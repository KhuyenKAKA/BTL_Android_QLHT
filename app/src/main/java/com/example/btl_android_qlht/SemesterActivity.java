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

public class SemesterActivity extends AppCompatActivity {

    // Khai báo biến layout cha để làm animation mượt hơn
    private LinearLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acivity_semester);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.semester), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Lấy layout container chứa danh sách các thẻ
        layoutContainer = findViewById(R.id.linear_list_container);

        setupItem(findViewById(R.id.item_hk1), "Học kỳ 1", "Khá", "75", "4", "79");
        setupItem(findViewById(R.id.item_hk2), "Học kỳ 2", "Tốt", "76", "6", "82");
        setupItem(findViewById(R.id.item_hk3), "Học kỳ 3", "Tốt", "77", "4", "81");
        setupItem(findViewById(R.id.item_hk4), "Học kỳ 4", "Tốt", "80", "6", "86");
    }

    private void setupItem(View itemView, String semester, String rank, String teacherScore, String bonusScore, String totalScore) {
        if (itemView == null) return;

        // --- SỬA LỖI QUAN TRỌNG ---
        // Vì item_semester.xml có root là CardView, nên itemView chính là CardView đó.
        // Không dùng findViewById để tìm CardView nữa vì nó sẽ trả về null.
        CardView cardView = (CardView) itemView;
        // ---------------------------

        TextView tvSemester = itemView.findViewById(R.id.tv_semester_title);
        TextView tvRank = itemView.findViewById(R.id.tv_rank);
        TextView tvTeacherScore = itemView.findViewById(R.id.tv_teacher_score);
        TextView tvBonusScore = itemView.findViewById(R.id.tv_bonus_score);
        TextView tvTotalScore = itemView.findViewById(R.id.tv_total_score);

        LinearLayout expandableLayout = itemView.findViewById(R.id.expandable_layout);
        ImageView imgArrow = itemView.findViewById(R.id.img_arrow);

        tvSemester.setText(semester);
        tvRank.setText(rank);
        tvTeacherScore.setText(teacherScore);
        tvBonusScore.setText(bonusScore);
        tvTotalScore.setText(totalScore);

        View.OnClickListener toggleListener = v -> {
            // Animation: Áp dụng lên layout cha (container) để các thẻ bên dưới bị đẩy xuống mượt mà
            if (layoutContainer != null) {
                TransitionManager.beginDelayedTransition(layoutContainer, new AutoTransition());
            } else {
                // Fallback nếu không tìm thấy container
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
            }

            if (expandableLayout.getVisibility() == View.GONE) {
                expandableLayout.setVisibility(View.VISIBLE);
                imgArrow.animate().rotation(180).setDuration(200).start(); // Xoay mũi tên lên có animation
            } else {
                expandableLayout.setVisibility(View.GONE);
                imgArrow.animate().rotation(0).setDuration(200).start(); // Xoay mũi tên xuống có animation
            }
        };

        cardView.setOnClickListener(toggleListener);
    }
}