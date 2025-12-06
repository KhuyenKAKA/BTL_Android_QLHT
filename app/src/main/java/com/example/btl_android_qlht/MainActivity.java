package com.example.btl_android_qlht;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.entity.News;
import com.example.btl_android_qlht.db.entity.Teacher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nested_scroll_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUser();
        loadLatestNotification();
        initEvents();
    }

    private void loadUser(){
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<Teacher> listUs = db.teacherDao().getAllTeachers();
        Log.d("USER_DATA", "Danh sách giáo viên: " + listUs.toString());
    }

    // Hàm load 1 thông báo mới nhất hiển thị lên CardView tĩnh
    private void loadLatestNotification() {
        AppDatabase db = AppDatabase.getDbInstance(this);
        List<News> newsList = db.newsDao().getAllNews();

        // Ánh xạ các view trong thẻ thông báo tĩnh
        CardView cardLatestNews = findViewById(R.id.card_latest_news);
        TextView tvTitle = findViewById(R.id.tv_latest_title);
        TextView tvContent = findViewById(R.id.tv_latest_content);
        TextView tvTime = findViewById(R.id.tv_latest_time);

        if (newsList != null && !newsList.isEmpty()) {
            // Lấy tin mới nhất (vị trí 0 vì Query đã sắp xếp DESC)
            News latestNews = newsList.get(0);

            tvTitle.setText(latestNews.getTitle());
            tvContent.setText(latestNews.getContent());
            tvTime.setText(latestNews.getTime());

            cardLatestNews.setVisibility(View.VISIBLE);
        } else {
            // Nếu không có tin nào, ẩn thẻ đi hoặc hiện thông báo "Không có tin mới"
            cardLatestNews.setVisibility(View.GONE);
        }
    }

    private void initEvents() {
        // 1. Sự kiện nút Đánh giá rèn luyện
        LinearLayout btnDgrl = findViewById(R.id.btn_dgrl);
        if (btnDgrl != null) {
            btnDgrl.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SemesterActivity.class);
                startActivity(intent);
            });
        }

        // 2. Sự kiện nút Xem thêm thông báo (MỞ NewsActivity)
        TextView btnViewAllNews = findViewById(R.id.view_all_notifications);
        if (btnViewAllNews != null) {
            btnViewAllNews.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout navSettings = findViewById(R.id.nav_settings);
        if (navSettings != null) {
            navSettings.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                // Flag để tránh tạo activity chồng chéo
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0, 0); // Tắt hiệu ứng chuyển cảnh
            });
        }
    }
}