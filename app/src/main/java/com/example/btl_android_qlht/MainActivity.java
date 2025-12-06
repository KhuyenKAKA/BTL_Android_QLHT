package com.example.btl_android_qlht;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.User;
import com.example.btl_android_qlht.db.entity.Teacher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loadUser();

        View btnRegister = findViewById(R.id.btn_registerCourses);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewInformationActivity.class);
                startActivity(intent);
            }
        });
        View btnTaiChinh = findViewById(R.id.btn_taichinh);
        btnTaiChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FinanceActivity.class);
                startActivity(intent);
            }
        });
    }



    private void loadUser(){
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<Teacher> listUs = db.teacherDao().getAllTeachers();
        Log.d("USER_DATA", "Danh sách giáo viên: " + listUs.toString());
    }


}