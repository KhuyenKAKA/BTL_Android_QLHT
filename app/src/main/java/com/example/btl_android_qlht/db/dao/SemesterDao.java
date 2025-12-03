package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Semester;

import java.util.List;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.Semester;

@Dao
public interface SemesterDao {
    @Query("SELECT * FROM Semester")
    List<Semester> getAllSemesters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Semester semester);
}
