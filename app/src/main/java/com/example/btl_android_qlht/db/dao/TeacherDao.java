package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Teacher;

import java.util.List;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Teacher;

@Dao
public interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Teacher teacher);

    @Query("SELECT * FROM Teacher")
    List<Teacher> getAllTeachers();
    @Query("SELECT * FROM Teacher WHERE id = :teacherId LIMIT 1")
    Teacher getTeacherById(String teacherId);

}
