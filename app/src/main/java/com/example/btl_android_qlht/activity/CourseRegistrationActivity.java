package com.example.btl_android_qlht.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_qlht.R;
import com.example.btl_android_qlht.adapter.CourseRegistrationAdapter;
import com.example.btl_android_qlht.db.AppDatabase;
import com.example.btl_android_qlht.db.entity.ClassCourse;
import com.example.btl_android_qlht.db.entity.Enrollment;

import java.util.List;

public class CourseRegistrationActivity extends AppCompatActivity {

    // Khai báo các biến thành viên
    private RecyclerView recyclerView;
    private CourseRegistrationAdapter adapter;
    private AppDatabase db;
    // ID của sinh viên duy nhất trong ứng dụng.
    // Trong một ứng dụng thực tế, ID này sẽ được lấy sau khi đăng nhập.
    private final String currentStudentId = "20205096"; // <-- THAY BẰNG ID SINH VIÊN CỐ ĐỊNH CỦA BẠN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Kết nối Activity với file layout của nó
        setContentView(R.layout.activity_course_registration);

        // Ánh xạ RecyclerView từ layout
        recyclerView = findViewById(R.id.recycler_view_courses);
        recyclerView.setHasFixedSize(true); // Tối ưu hiệu năng

        // Khởi tạo đối tượng Database
        db = AppDatabase.getDbInstance(this.getApplicationContext());

        // Bắt đầu tải dữ liệu để hiển thị
        loadAvailableCourses();
    }

    /**
     * Tải danh sách các lớp học phần mà sinh viên chưa đăng ký và hiển thị lên RecyclerView.
     */
    private void loadAvailableCourses() {
        // Lấy dữ liệu từ DAO.
        // Do bạn đã cấu hình .allowMainThreadQueries(), ta có thể gọi trực tiếp ở đây.
        List<ClassCourse> availableCourses = db.enrollmentDao().getAvailableClassesForStudent(currentStudentId);

        // Khởi tạo Adapter, truyền vào danh sách dữ liệu và một listener để xử lý sự kiện click.
        adapter = new CourseRegistrationAdapter(availableCourses, classCourse -> {
            // Khi người dùng nhấn nút "Đăng ký" trong một item, phương thức này sẽ được gọi.
            registerForCourse(classCourse);
        });

        // Gán Adapter vừa tạo cho RecyclerView.
        recyclerView.setAdapter(adapter);
    }

    /**
     * Thực hiện logic ghi danh một lớp học phần.
     * @param course Lớp học phần mà sinh viên muốn đăng ký.
     */
    private void registerForCourse(ClassCourse course) {
        // 1. Tạo một đối tượng Enrollment mới với ID của sinh viên và ID của lớp học.
        Enrollment newEnrollment = new Enrollment();
        newEnrollment.studentId = currentStudentId;
        newEnrollment.classId = course.id;
        // Các trường điểm số sẽ có giá trị mặc định là 0.

        // 2. Gọi DAO để thêm bản ghi mới này vào database.
        db.enrollmentDao().insert(newEnrollment);

        // 3. Hiển thị một thông báo ngắn để người dùng biết đã thành công.
        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        // 4. Tải lại danh sách để cập nhật giao diện.
        // Lớp học vừa đăng ký sẽ không còn xuất hiện trong danh sách nữa.
        loadAvailableCourses();
    }
}
