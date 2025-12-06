package com.example.btl_android_qlht;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottom_navigation), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initEvents();
    }

    private void initEvents() {
        // 1. Chức năng Feedback
        findViewById(R.id.btn_feedback).setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng Feedback đang phát triển", Toast.LENGTH_SHORT).show();
        });

        // 2. Chức năng Chế độ tối
        SwitchCompat switchDarkMode = findViewById(R.id.switch_dark_mode);
        // Kiểm tra trạng thái hiện tại (Giả lập)
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        switchDarkMode.setChecked(currentMode == AppCompatDelegate.MODE_NIGHT_YES);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "Đã bật chế độ tối", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "Đã tắt chế độ tối", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Chức năng Thoát ứng dụng
        findViewById(R.id.btn_exit).setOnClickListener(v -> {
            finishAffinity(); // Đóng tất cả Activity trong stack
            System.exit(0);   // Thoát hoàn toàn
        });

        // 4. Bottom Navigation: Quay lại Trang chủ
        LinearLayout navHome = findViewById(R.id.nav_home);
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            // Flags để clear stack và quay về Main mới thay vì tạo chồng Activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(0, 0); // Tắt hiệu ứng chuyển cảnh cho giống tab switching
        });

        // Nút Cài đặt (đang ở đây nên không làm gì)
        // Nút Cá nhân (Nếu có activity cá nhân thì thêm intent tương tự navHome)
    }
}