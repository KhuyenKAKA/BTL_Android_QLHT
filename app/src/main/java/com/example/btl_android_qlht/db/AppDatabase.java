package com.example.btl_android_qlht.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.btl_android_qlht.db.dao.ClassCourseDao;
import com.example.btl_android_qlht.db.dao.EnrollmentDao;
import com.example.btl_android_qlht.db.dao.EvaluationDao;
import com.example.btl_android_qlht.db.dao.ExaminationDao;
import com.example.btl_android_qlht.db.dao.NewsDao;
import com.example.btl_android_qlht.db.dao.SemesterDao;
import com.example.btl_android_qlht.db.dao.StudentDao;
import com.example.btl_android_qlht.db.dao.SubjectDao;
import com.example.btl_android_qlht.db.dao.TeacherDao;
import com.example.btl_android_qlht.db.dao.TransactionDao;
import com.example.btl_android_qlht.db.dao.WalletDao;
import com.example.btl_android_qlht.db.entity.ClassCourse;
import com.example.btl_android_qlht.db.entity.Enrollment;
import com.example.btl_android_qlht.db.entity.Evaluation;
import com.example.btl_android_qlht.db.entity.Examination;
import com.example.btl_android_qlht.db.entity.News;
import com.example.btl_android_qlht.db.entity.Semester;
import com.example.btl_android_qlht.db.entity.Student;
import com.example.btl_android_qlht.db.entity.Subject;
import com.example.btl_android_qlht.db.entity.Teacher;
import com.example.btl_android_qlht.db.entity.Transaction;
import com.example.btl_android_qlht.db.entity.Wallet;


import java.util.concurrent.Executors;

@Database(
        entities = {
                Student.class,
                Teacher.class,
                Wallet.class,
                Semester.class,
                Subject.class,
                ClassCourse.class,
                Enrollment.class,
                Examination.class,
                Evaluation.class,
                Transaction.class,
                News.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract WalletDao walletDao();
    public abstract SemesterDao semesterDao();
    public abstract SubjectDao subjectDao();
    public abstract ClassCourseDao classDao();
    public abstract EnrollmentDao enrollmentDao();
    public abstract ExaminationDao examinationDao();
    public abstract EvaluationDao evaluationDao();
    public abstract TransactionDao transactionDao();
    public abstract NewsDao newsDao();

    private static AppDatabase INSTANCE;
    public static  AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "QLHTkhuyen_db"
                    )
                    .addCallback(seedCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback seedCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate( SupportSQLiteDatabase db) {
            super.onCreate(db);

            Executors.newSingleThreadExecutor().execute(() -> {

                AppDatabase database = INSTANCE;

                // ----------------------------
                // 1. Students
                // ----------------------------
                database.studentDao().insert(new Student(
                        "S01", "Nguyễn Văn A", "CNTTTA01", "2003-05-10",
                        "CNTT", "CNTT", "Hà Nội", "0988999999",
                        "a@gmail.com", 1, "012345678"
                ));
                database.studentDao().insert(new Student(
                        "S02", "Trần Thị B", "KTPM1", "2004-11-20",
                        "CNTT", "KTPM", "HCM", "0977222333",
                        "b@gmail.com", 0, "987654321"
                ));

                // ----------------------------
                // 2. Wallet
                // ----------------------------
                database.walletDao().insert(new Wallet("S01", 2000000, 0));
                database.walletDao().insert(new Wallet("S02", 500000, 200000));

                // ----------------------------
                // 3. Teacher
                // ----------------------------
                database.teacherDao().insert(new Teacher("T01", "Thầy Minh", "0901122334", "ngothitukhuyen2903@gmail.com"));
                database.teacherDao().insert(new Teacher("T02", "Cô Lan", "0911456677", "lan@univ.com"));

                // ----------------------------
                // 4. Semester
                // ----------------------------
                database.semesterDao().insert(new Semester("20241", "Học kỳ 1 - 2024"));
                database.semesterDao().insert(new Semester("20242", "Học kỳ 2 - 2024"));

                // ----------------------------
                // 5. Subject
                // ----------------------------
                database.subjectDao().insert(new Subject("SUB01", "Lập Trình Java", 3, 550000));
                database.subjectDao().insert(new Subject("SUB02", "Cơ sở dữ liệu", 3, 600000));

                // ----------------------------
                // 6. ClassCourse
                // ----------------------------
                database.classDao().insert(new ClassCourse(
                        "C01", "Java - Lớp 1", "20241", "SUB01", "T01",
                        "A101", "2025-12-01 08:00", "2025-12-01 10:00", 50
                ));

                database.classDao().insert(new ClassCourse(
                        "C02", "CSDL - Lớp 2", "20241", "SUB02", "T02",
                        "A102", "2025-12-03 13:00", "2025-12-03 15:00", 45
                ));

                // ----------------------------
                // 7. Enrollment (Student Register Class)
                // ----------------------------
                database.enrollmentDao().insert(new Enrollment(
                        0, "C01", "S01",
                        8.0f, 7.5f, 9.0f, 8.17f, "A"
                ));
                database.enrollmentDao().insert(new Enrollment(
                        0, "C02", "S02",
                        7.0f, 7.0f, 8.0f, 7.33f, "B"
                ));

                // ----------------------------
                // 8. Evaluation (Student Conduct)
                // ----------------------------
                database.evaluationDao().insert(new Evaluation(
                        0, "S01", "20241", 85, 10, "Xuất sắc"
                ));
                database.evaluationDao().insert(new Evaluation(
                        0, "S02", "20241", 80, 5, "Tốt"
                ));

                // ----------------------------
                // 9. Examination (Final Exam Schedule)
                // ----------------------------
                database.examinationDao().insert(new Examination(
                        0, "SUB01", "20241", "2025-12-10 08:00", "P201", 0
                ));
                database.examinationDao().insert(new Examination(
                        0, "SUB02", "20241", "2025-12-12 13:00", "P202", 0
                ));

                // ----------------------------
                // 10. Transactions (Student Payment + Tuition)
                // ----------------------------
                database.transactionDao().insert(new Transaction(
                        0, "S01", "SUB01", 1650000, "2025-12-01 09:00"
                ));
                database.transactionDao().insert(new Transaction(
                        0, "S01", null, 500000, "2025-12-02 10:00"   // top-up
                ));
                database.transactionDao().insert(new Transaction(
                        0, "S02", "SUB02", 1800000, "2025-12-03 11:00"
                ));
                database.newsDao().insert(new News(
                        1,
                        "Thông báo lịch thi học kỳ 1",
                        "Sinh viên chú ý xem lịch thi trên cổng thông tin.",
                        "2025-12-01 09:30"
                ));
                database.newsDao().insert(new News(
                        2,
                        "Tuyển dụng Internship FPT Software",
                        "FPT Software mở đơn tuyển thực tập sinh cho sinh viên năm 3-4.",
                        "2025-12-03 10:15"
                ));
                database.newsDao().insert(new News(
                        3,
                        "Tuyển dụng Internship Samsung SRV",
                        "Samsung SRV mở đơn tuyển thực tập sinh cho sinh viên năm 3-4.",
                        "2025-11-07 08:30"
                ));
            });
        }
    };
}
