package com.example.btl_android_qlht;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.dto.StudentProfileDTO;

import java.text.DecimalFormat;

public class ViewInformationActivity extends AppCompatActivity {

    // 1. Khai báo các biến giao diện
    private TextView tvName, tvClass, tvFaculty, tvMssv, tvGender, tvBalance, tvDebt;
    private EditText edtDob, edtEmail, edtPhone, edtAddress;
    private String currentStudentId = "S01";
    private boolean isEditing = false;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);
        setupActionBar();
        initViews();
        loadData();
    }

    private void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Thông tin sinh viên");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        tvName = findViewById(R.id.tv_profile_name);
        tvClass = findViewById(R.id.tv_class_value);
        tvFaculty = findViewById(R.id.tv_faculty_value);
        tvMssv = findViewById(R.id.tv_mssv_value);
        edtDob = findViewById(R.id.edt_dob_value);
        tvGender = findViewById(R.id.tv_gender_value);
        edtEmail = findViewById(R.id.edt_email_value);
        edtPhone = findViewById(R.id.edt_phone_value);
        edtAddress = findViewById(R.id.edt_address_value);
        tvBalance = findViewById(R.id.tv_wallet_balance);
        tvDebt = findViewById(R.id.tv_wallet_debt);
    }

    private void loadData() {
        AppDatabase db = AppDatabase.getDbInstance(this);
        StudentProfileDTO profile = db.studentDao().getStudentProfile(currentStudentId);

        if (profile != null) {
            tvName.setText(profile.fullName);
            tvClass.setText(profile.className);
            tvFaculty.setText(profile.faculty);

            tvMssv.setText(profile.mssv);
            edtDob.setText(profile.dob);
            tvGender.setText(profile.getGenderText());

            edtEmail.setText(profile.email);
            edtPhone.setText(profile.phoneNumber);
            edtAddress.setText(profile.address);

            // Format tiền tệ
            DecimalFormat formatter = new DecimalFormat("#,### đ");

            if (tvBalance != null) {
                tvBalance.setText(formatter.format(profile.walletBalance));
            }
            if (tvDebt != null) {
                tvDebt.setText(formatter.format(profile.walletDebt));
            }
        } else {
            Toast.makeText(this, "Không tìm thấy dữ liệu cho MSSV: " + currentStudentId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        mMenu = menu; // Lưu lại menu để lát nữa đổi icon
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.action_edit) {
            if (isEditing) {
                saveData();
            } else {
                // Đang ở chế độ Xem -> Bấm phát là SỬA
                enableEditMode(true);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableEditMode(boolean enable) {
        isEditing = enable;
        edtDob.setEnabled(enable);
        edtEmail.setEnabled(enable);
        edtPhone.setEnabled(enable);
        edtAddress.setEnabled(enable);

        if (enable) {
            edtEmail.requestFocus();
            int editableColor = getResources().getColor(R.color.white);
            edtDob.setBackgroundColor(editableColor);
            edtEmail.setBackgroundColor(editableColor);
            edtPhone.setBackgroundColor(editableColor);
            edtAddress.setBackgroundColor(editableColor);
            mMenu.findItem(R.id.action_edit).setIcon(android.R.drawable.ic_menu_save);
        } else {
            edtDob.setBackgroundResource(android.R.color.transparent);
            edtEmail.setBackgroundResource(android.R.color.transparent);
            edtPhone.setBackgroundResource(android.R.color.transparent);
            edtAddress.setBackgroundResource(android.R.color.transparent);
            mMenu.findItem(R.id.action_edit).setIcon(R.drawable.ic_person);
        }
    }

    private void saveData() {
        // 1. Lấy dữ liệu mới từ EditText
        String newDob = edtDob.getText().toString();
        String newEmail = edtEmail.getText().toString();
        String newPhone = edtPhone.getText().toString();
        String newAddress = edtAddress.getText().toString();

        // 2. Update vào Database
        AppDatabase db = AppDatabase.getDbInstance(this);
        db.studentDao().updateContactInfo(currentStudentId, newDob, newEmail, newPhone, newAddress);

        Toast.makeText(this, "Đã cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();

        // 3. Quay về chế độ xem
        enableEditMode(false);
    }
}