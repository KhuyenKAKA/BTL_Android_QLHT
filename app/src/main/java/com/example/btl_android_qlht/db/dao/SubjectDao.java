package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Subject;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.Subject;

@Dao
public interface SubjectDao {
    @Query("SELECT * FROM Subject WHERE id = :subjectId LIMIT 1")
    Subject getSubjectById(String subjectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Subject subject);

}
