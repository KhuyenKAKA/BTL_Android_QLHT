package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Student")
public class Student {
    @PrimaryKey
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "class_name")
    public String className;

    @ColumnInfo(name = "dob")
    public String dob; // yyyy-MM-dd (or any string format)

    @ColumnInfo(name = "faculty")
    public String faculty;

    @ColumnInfo(name = "major")
    public String major;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "gender")
    public int gender;
    @ColumnInfo(name = "CID")
    public String CID;

    public Student() {}
}
