package com.example.btl_android_qlht.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_qlht.R;
import com.example.btl_android_qlht.db.entity.ClassCourse;

import java.util.List;

public class CourseRegistrationAdapter extends RecyclerView.Adapter<CourseRegistrationAdapter.CourseViewHolder> {

    private List<ClassCourse> courseList;
    private final OnRegisterClickListener listener;

    // Interface để xử lý sự kiện click nút Đăng ký
    public interface OnRegisterClickListener {
        void onRegisterClick(ClassCourse classCourse);
    }

    // Constructor nhận dữ liệu và listener
    public CourseRegistrationAdapter(List<ClassCourse> courseList, OnRegisterClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo View cho một item bằng cách inflate layout XML
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_course, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        // Lấy dữ liệu tại vị trí hiện tại
        ClassCourse currentCourse = courseList.get(position);
        // Gán dữ liệu cho ViewHolder
        holder.bind(currentCourse, listener);
    }

    @Override
    public int getItemCount() {
        // Trả về tổng số item
        return courseList != null ? courseList.size() : 0;
    }

    // Lớp ViewHolder chịu trách nhiệm quản lý các View của một item
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        // Khai báo các View từ file list_item_course.xml
        private final TextView tvCourseName;
        private final TextView tvTeacherName;
        private final TextView tvSchedule;
        private final TextView tvSlots;
        private final Button btnRegister;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ View
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvTeacherName = itemView.findViewById(R.id.tv_teacher_name);
            tvSchedule = itemView.findViewById(R.id.tv_schedule);
            tvSlots = itemView.findViewById(R.id.tv_slots);
            btnRegister = itemView.findViewById(R.id.btn_register);
        }

        // Phương thức để gán dữ liệu vào các View
        public void bind(final ClassCourse classCourse, final OnRegisterClickListener listener) {
            // TODO: Lấy tên môn học, tên giảng viên từ DB dựa vào ID và hiển thị
            // Tạm thời hiển thị ID
            tvCourseName.setText("Mã môn học: " + classCourse.subjectId);
            tvTeacherName.setText("Mã GV: " + classCourse.teacherId);

            // Hiển thị các thông tin khác
            String schedule = "Lịch: " + classCourse.startTime + " - " + classCourse.endTime;
            tvSchedule.setText(schedule);

            // Chỗ này bạn cần lấy sĩ số đã đăng ký, tạm thời để là 0
            String slots = "Sĩ số: 0/" + classCourse.quantity;
            tvSlots.setText(slots);

            // Xử lý sự kiện click cho nút Đăng ký
            btnRegister.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRegisterClick(classCourse);
                }
            });
        }
    }
}
