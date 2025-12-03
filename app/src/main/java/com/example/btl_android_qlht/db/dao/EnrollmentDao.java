package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.btl_android_qlht.db.entity.Enrollment;

@Dao
public interface EnrollmentDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insert(Enrollment enrollment);
}
