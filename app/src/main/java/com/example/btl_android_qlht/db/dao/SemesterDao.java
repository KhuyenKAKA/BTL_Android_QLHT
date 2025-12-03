package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.Semester;

@Dao
public interface SemesterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Semester semester);
}
