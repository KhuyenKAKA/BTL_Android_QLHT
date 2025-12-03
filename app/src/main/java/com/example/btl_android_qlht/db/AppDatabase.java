package com.example.btl_android_qlht.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                News.class,
                User.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  UserDao userDao();
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
                            "QLHT_db"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
