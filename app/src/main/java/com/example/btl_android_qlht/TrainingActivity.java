package com.example.btl_android_qlht;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acivity_training);

        // Xử lý Insets để tránh bị tai thỏ/thanh điều hướng che mất nội dung (chuẩn EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.training), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        // Nút Back
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Thiết lập dữ liệu cho các item (Hardcode mẫu, sau này bạn có thể thay bằng DB)
        // Lưu ý: ID item_hk1, item_hk2... phải khớp với ID thẻ <include> trong file XML
        setupItem(findViewById(R.id.item_hk1), "Học kỳ 1", "Khá", "75", "4", "79");
        setupItem(findViewById(R.id.item_hk2), "Học kỳ 2", "Tốt", "76", "6", "82");
        setupItem(findViewById(R.id.item_hk3), "Học kỳ 3", "Tốt", "77", "4", "81");
        // setupItem(findViewById(R.id.item_hk4), "Học kỳ 4", "Tốt", "80", "6", "86");
    }

    /**
     * Hàm thiết lập dữ liệu và sự kiện click cho từng thẻ đánh giá
     */
    private void setupItem(View itemView, String semester, String rank, String teacherScore, String bonusScore, String totalScore) {
        if (itemView == null) return;

        // 1. Ánh xạ Views bên trong Item
        TextView tvSemester = itemView.findViewById(R.id.tv_semester_title);
        TextView tvRank = itemView.findViewById(R.id.tv_rank);
        TextView tvTeacherScore = itemView.findViewById(R.id.tv_teacher_score);
        TextView tvBonusScore = itemView.findViewById(R.id.tv_bonus_score);
        TextView tvTotalScore = itemView.findViewById(R.id.tv_total_score);

        LinearLayout expandableLayout = itemView.findViewById(R.id.expandable_layout);
        CardView cardView = itemView.findViewById(R.id.card_view_container);
        ImageView imgArrow = itemView.findViewById(R.id.img_arrow);

        // 2. Gán dữ liệu
        tvSemester.setText(semester);
        tvRank.setText(rank);
        tvTeacherScore.setText(teacherScore);
        tvBonusScore.setText(bonusScore);
        tvTotalScore.setText(totalScore);

        // 3. Xử lý logic Click để Đóng/Mở
        View.OnClickListener toggleListener = v -> {
            // Hiệu ứng mượt mà (Transition)
            if (cardView != null) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
            }

            if (expandableLayout.getVisibility() == View.GONE) {
                // Đang đóng -> Mở ra
                expandableLayout.setVisibility(View.VISIBLE);
                imgArrow.setRotation(180); // Xoay mũi tên lên
            } else {
                // Đang mở -> Đóng lại
                expandableLayout.setVisibility(View.GONE);
                imgArrow.setRotation(0); // Xoay mũi tên xuống
            }
        };

        // Gán sự kiện click cho toàn bộ thẻ
        if (cardView != null) {
            cardView.setOnClickListener(toggleListener);
        }
    }
}