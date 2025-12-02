package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Transaction",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "studentId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subjectId", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Transaction {
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

    public Transaction() {}
}