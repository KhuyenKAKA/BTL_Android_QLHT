package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btl_android_qlht.db.entity.ClassCourse;
import com.example.btl_android_qlht.db.entity.Enrollment;

import java.util.List;
import androidx.room.Insert;

import com.example.btl_android_qlht.db.entity.Enrollment;

@Dao
public interface EnrollmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);
    @Query("SELECT * FROM Enrollment WHERE student_id = :studentId")
    List<Enrollment> getEnrollmentsForStudent(String studentId);

    @Query("SELECT * FROM Enrollment WHERE student_id = :studentId AND class_id = :classId LIMIT 1")
    Enrollment getEnrollment(String studentId, String classId);

    @Query("SELECT * FROM Class WHERE id NOT IN (SELECT class_id FROM Enrollment WHERE student_id = :studentId)")
    List<ClassCourse> getAvailableClassesForStudent(String studentId);

}
