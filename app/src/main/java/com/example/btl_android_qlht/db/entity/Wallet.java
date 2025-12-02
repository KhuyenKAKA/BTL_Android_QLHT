package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Wallet",
        foreignKeys = @ForeignKey(
                entity = Student.class,
                parentColumns = "id",
                childColumns = "studentId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Wallet {
    @PrimaryKey
    public String studentId; // 1-1 với Student.id
    @ColumnInfo(name = "balance")
    public double balance;
    @ColumnInfo(name = "debt")
    public double debt;

    public Wallet() {}
}