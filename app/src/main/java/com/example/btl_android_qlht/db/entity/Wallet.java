package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
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
    @NonNull
    @PrimaryKey
    public String studentId; // 1-1 với Student.id
    @ColumnInfo(name = "balance")
    public double balance;
    @ColumnInfo(name = "debt")
    public double debt;


    public Wallet(String studentId, double balance, double debt) {
        this.studentId = studentId;
        this.balance = balance;
        this.debt = debt;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "studentId='" + studentId + '\'' +
                ", balance=" + balance +
                ", debt=" + debt +
                '}';
    }
}