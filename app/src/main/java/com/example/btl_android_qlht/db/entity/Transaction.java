package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Transaction",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "student_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subject_id", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Transaction {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "student_id")
    public String studentId;

    @ColumnInfo(name = "subject_id")
    public String subjectId; // nullable when deposit top-up
    @ColumnInfo(name = "amount")
    public double amount;
    @ColumnInfo(name = "time")
    public String time;


    public Transaction(int id, String studentId, String subjectId, double amount, String time) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.amount = amount;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Ignore
    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                '}';
    }
}