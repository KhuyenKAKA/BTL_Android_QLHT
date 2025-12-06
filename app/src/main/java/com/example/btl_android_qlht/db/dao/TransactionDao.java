package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction tx);
    @Query("SELECT * FROM `Transaction` WHERE student_id = :studentId ORDER BY id DESC")
    List<Transaction> getTransactionsByStudent(String studentId);
}
