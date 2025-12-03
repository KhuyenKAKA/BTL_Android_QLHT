package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.ClassCourse;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.ClassCourse;

import java.util.List;

@Dao
public interface ClassCourseDao {
    @Query("SELECT * FROM Class") // Tên bảng là "Class" như trong Entity
    List<ClassCourse> getAll();

    @Query("SELECT * FROM Class WHERE id = :classId")
    ClassCourse getClassById(String classId);

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insert(ClassCourse classEntity);

}
