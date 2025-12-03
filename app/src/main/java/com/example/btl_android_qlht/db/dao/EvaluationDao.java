package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.btl_android_qlht.db.entity.Evaluation;

@Dao
public interface EvaluationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Evaluation eval);
}
