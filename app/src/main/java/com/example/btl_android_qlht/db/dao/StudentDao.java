package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Student;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.Student;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student WHERE id = :studentId LIMIT 1")
    Student getStudentById(String studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);
}
