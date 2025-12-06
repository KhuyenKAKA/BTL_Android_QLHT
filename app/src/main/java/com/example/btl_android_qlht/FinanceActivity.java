package com.example.btl_android_qlht;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.entity.Transaction;
import com.example.btl_android_qlht.db.entity.Wallet;
import com.example.btl_android_qlht.adapter.TransactionAdapter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FinanceActivity extends AppCompatActivity {

    private TextView tvBalance, tvDebt;
    private RecyclerView rcvHistory;
    private Button btnTopUp;
    private TransactionAdapter adapter;
    private AppDatabase db;
    private Wallet currentWallet;

    private String currentStudentId = "S01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        initViews();
        db = AppDatabase.getDbInstance(this);

        loadWalletData();
        loadTransactionHistory();

        // 1. Sự kiện nút Nạp tiền (Hiển thị QR)
        btnTopUp.setOnClickListener(v -> showDepositDialog());
        findViewById(R.id.layout_actions_export).setOnClickListener(v -> {
            exportToExcel();
        });

        // Gắn sự kiện cho nút "In hóa đơn" (Tìm theo ID nếu bạn chưa đặt ID thì cần đặt thêm trong XML)
        // Ví dụ: android:id="@+id/btn_print_invoice" cho cái LinearLayout bao quanh nút In
        // Tạm thời mình giả lập gọi hàm:

    }

    private void initViews() {
        tvBalance = findViewById(R.id.layout_balance).findViewById(R.id.tv_balance_value);
        rcvHistory = findViewById(R.id.rv_transactions);
        btnTopUp = findViewById(R.id.btn_top_up);
        adapter = new TransactionAdapter(new ArrayList<>());
        rcvHistory.setLayoutManager(new LinearLayoutManager(this));
        rcvHistory.setAdapter(adapter);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
    private void loadWalletData() {
        currentWallet = db.walletDao().getWalletByStudentId(currentStudentId);
        if (currentWallet != null) {
            DecimalFormat formatter = new DecimalFormat("#,### đ");
            // Ánh xạ lại TextView cho chắc chắn (Bạn nhớ thêm ID trong XML nhé)
            // Ví dụ: ((TextView)findViewById(R.id.tv_balance)).setText(...)

            // Demo logic:
            // tvBalance.setText(formatter.format(currentWallet.balance));
            // tvDebt.setText(formatter.format(currentWallet.debt));
        }
    }

    private void loadTransactionHistory() {
        List<Transaction> list = db.transactionDao().getTransactionsByStudent(currentStudentId);
        adapter.setData(list);
    }

    // --- PHẦN 2: CHỨC NĂNG NẠP TIỀN (QR CODE) ---
    private void showDepositDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_deposit);

        EditText edtAmount = dialog.findViewById(R.id.edt_amount);
        Button btnGenQR = dialog.findViewById(R.id.btn_gen_qr);
        ImageView imgQR = dialog.findViewById(R.id.img_qr);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm_payment);

        // Bước 1: Nhập tiền -> Bấm tạo QR
        btnGenQR.setOnClickListener(v -> {
            String amountStr = edtAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                // Sử dụng API VietQR để tạo ảnh QR
                // Format: https://img.vietqr.io/image/[BANK_ID]-[ACC_NO]-[TEMPLATE].png?amount=[AMOUNT]&addInfo=[TEXT]
                // Ví dụ: MBBank, STK: 0988999999
                String bankId = "MB";
                String accountNo = "0988999999";
                String content = "NAP TIEN " + currentStudentId;

                String url = "https://img.vietqr.io/image/" + bankId + "-" + accountNo + "-compact.png?amount=" + amountStr + "&addInfo=" + content;

                Glide.with(this).load(url).into(imgQR);
            }
        });

        // Bước 2: Giả lập "Đã thanh toán thành công"
        // (Thực tế cần Backend hook, ở đây ta update DB luôn)
        btnConfirm.setOnClickListener(v -> {
            String amountStr = edtAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);

                double newBalance = currentWallet.balance + amount;
                db.walletDao().updateBalance(currentStudentId, newBalance);

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                Transaction trans = new Transaction(0, currentStudentId, "Nạp tiền qua QR", amount, timeStamp);
                db.transactionDao().insert(trans);

                loadWalletData();
                loadTransactionHistory();
                Toast.makeText(this, "Nạp tiền thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void exportToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Lịch sử giao dịch");
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        String[] columns = {"ID", "Nội dung giao dịch", "Tên môn học", "Số tiền (VNĐ)", "Thời gian"};
        for (int i = 0; i < columns.length; i++) {
            org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }
        List<Transaction> list = db.transactionDao().getTransactionsByStudent(currentStudentId);
        DecimalFormat formatter = new DecimalFormat("#,###");
        int rowNum = 1;
        for (Transaction t : list) {
            Row row = sheet.createRow(rowNum++);

            // Cột 0: ID
            row.createCell(0).setCellValue(t.getId());

            // Cột 1: Nội dung (Xử lý logic dựa trên subjectId)
            String description;
            String subjectCode = "";

            if (t.getSubjectId() == null || t.getSubjectId().isEmpty()) {
                description = "Nạp tiền vào tài khoản";
                subjectCode = "N/A";
            } else {
                description = "Thanh toán học phí";
                subjectCode = t.getSubjectId();
            }
            row.createCell(1).setCellValue(description);

            // Cột 2: Mã môn học
            row.createCell(2).setCellValue(subjectCode);

            // Cột 3: Số tiền (Format đẹp hoặc để số thô tùy bạn)
            // Ở đây mình để số thô (double) để vào Excel có thể tính toán được,
            // nhưng hiển thị kèm format text thì dùng dòng dưới
            row.createCell(3).setCellValue(formatter.format(t.getAmount()));

            // Cột 4: Thời gian
            row.createCell(4).setCellValue(t.getTime());
        }

        // Tự động giãn cột cho đẹp
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // 4. Lưu file ra bộ nhớ
        String fileName = "SaoKe_" + currentStudentId + "_" + System.currentTimeMillis() + ".xlsx";
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);

        try (FileOutputStream os = new FileOutputStream(file)) {
            workbook.write(os);
            workbook.close();

            Toast.makeText(this, "Xuất Excel thành công!\nLưu tại: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

            // (Tùy chọn) Mở file ngay sau khi xuất
            // openFile(file);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi xuất file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}