package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.ClassCourse;

@Dao
public interface ClassCourseDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insert(ClassCourse classEntity);

}