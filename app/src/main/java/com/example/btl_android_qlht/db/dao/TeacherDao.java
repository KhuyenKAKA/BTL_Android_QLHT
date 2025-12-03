package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Teacher;

@Dao
public interface TeacherDao {
    @Query("SELECT * FROM Teacher WHERE id = :teacherId LIMIT 1")
    Teacher getTeacherById(String teacherId);

}
