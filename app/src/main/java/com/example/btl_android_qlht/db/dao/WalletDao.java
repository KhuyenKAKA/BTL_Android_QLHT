package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btl_android_qlht.db.entity.Wallet;

@Dao
public interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Wallet wallet);

    @Query("SELECT * FROM Wallet WHERE studentId = :studentId")
    Wallet getWalletByStudentId(String studentId);

    @Query("UPDATE Wallet SET balance = :newBalance WHERE studentId = :studentId")
    void updateBalance(String studentId, double newBalance);

    @Query("UPDATE Wallet SET debt = :newDebt WHERE studentId = :studentId")
    void updateDebt(String studentId, double newDebt);

    @Update
    void update(Wallet wallet);
}
