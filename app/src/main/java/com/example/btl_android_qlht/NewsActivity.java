package com.example.btl_android_qlht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.entity.News;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        // Xử lý EdgeToEdge để không bị che bởi thanh trạng thái
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rcv_notifications), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Xử lý nút Back
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // 2. Cấu hình RecyclerView
        RecyclerView rcvNotification = findViewById(R.id.rcv_notifications);
        rcvNotification.setLayoutManager(new LinearLayoutManager(this));

        // 3. Lấy dữ liệu từ DB
        AppDatabase db = AppDatabase.getDbInstance(this);
        List<News> allNews = db.newsDao().getAllNews();

        // 4. Gán Adapter
        NotificationListAdapter adapter = new NotificationListAdapter(allNews);
        rcvNotification.setAdapter(adapter);
    }

    // --- Inner Class Adapter (Adapter dùng cho list dọc) ---
    private static class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NewsViewHolder> {
        private final List<News> mList;

        public NotificationListAdapter(List<News> mList) {
            this.mList = mList;
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Liên kết với layout item_news.xml (thẻ có viền)
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
            News news = mList.get(position);
            if (news == null) return;

            holder.tvTitle.setText(news.getTitle());
            holder.tvContent.setText(news.getContent());
            holder.tvTime.setText(news.getTime());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        static class NewsViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvContent, tvTime;

            public NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvContent = itemView.findViewById(R.id.tv_content);
                tvTime = itemView.findViewById(R.id.tv_time);
            }
        }
    }
}