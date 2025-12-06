//package com.example.btl_android_qlht;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.btl_android_qlht.db.AppDatabase;
//import com.example.btl_android_qlht.db.dto.StudentProfileDTO;
//
//import java.text.DecimalFormat;
//
//public class ProfileActivity extends AppCompatActivity {
//
//    // Khai báo các biến View
//    private TextView tvName, tvClass, tvFaculty;
//    private TextView tvMssv, tvDob, tvGender;
//    private TextView tvEmail, tvPhone, tvAddress;
//    private TextView tvBalance, tvDebt;
//    private String currentStudentId = "S01";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_information);
//
//        initViews();
//        loadData();
//    }
//
//    private void initViews() {
//        tvName = findViewById(R.id.tv_profile_name);
//        tvClass = findViewById(R.id.tv_class_value);
//        tvFaculty = findViewById(R.id.tv_faculty_value);
//        tvMssv = findViewById(R.id.tv_mssv_value);
//        tvDob = findViewById(R.id.tv_dob_value);
//        tvGender = findViewById(R.id.tv_gender_value);
//        tvEmail = findViewById(R.id.tv_email_value);
//        tvPhone = findViewById(R.id.tv_phone_value);
//        tvAddress = findViewById(R.id.tv_address_value);
//        tvBalance = findViewById(R.id.tv_wallet_balance);
//        tvDebt = findViewById(R.id.tv_wallet_debt);
//    }
//
//    private void loadData() {
//        AppDatabase db = AppDatabase.getDbInstance(this);
//        StudentProfileDTO profile = db.studentDao().getStudentProfile(currentStudentId);
//
//        if (profile != null) {
//            tvName.setText(profile.fullName);
//            tvClass.setText(profile.className);
//            tvFaculty.setText(profile.faculty);
//            tvMssv.setText(profile.mssv);
//            tvDob.setText(profile.dob);
//            tvGender.setText(profile.getGenderText()); // Hàm chuyển 1 -> Nam
//            tvEmail.setText(profile.email);
//            tvPhone.setText(profile.phoneNumber);
//            tvAddress.setText(profile.address);
//            DecimalFormat formatter = new DecimalFormat("#,### đ");
//            if (tvBalance != null) {
//                tvBalance.setText(formatter.format(profile.walletBalance));
//            }
//            if (tvDebt != null) {
//                tvDebt.setText(formatter.format(profile.walletDebt));
//            }
//        } else {
//            Toast.makeText(this, "Không tìm thấy dữ liệu sinh viên " + currentStudentId, Toast.LENGTH_SHORT).show();
//        }
//    }
//}