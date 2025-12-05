package com.example.btl_android_qlht;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.entity.Teacher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Xử lý Insets cho màn hình chính (nếu cần thiết để tránh bị che bởi thanh trạng thái)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nested_scroll_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUser();
        initEvents();
    }

    private void loadUser(){
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<Teacher> listUs = db.teacherDao().getAllTeachers();
        Log.d("USER_DATA", "Danh sách giáo viên: " + listUs.toString());
    }

    private void initEvents() {
        // Tìm button (LinearLayout) Đánh giá rèn luyện theo ID trong file xml
        LinearLayout btnDgrl = findViewById(R.id.btn_dgrl);

        // Bắt sự kiện click
        btnDgrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển từ MainActivity sang SemesterActivity
                Intent intent = new Intent(MainActivity.this, SemesterActivity.class);
                startActivity(intent);
            }
        });
    }
}