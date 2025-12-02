package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Semester")
public class Semester {
    @PrimaryKey
    public String id; // ví dụ: "20241"

    @ColumnInfo(name = "name")
    public String name;

    public Semester() {}
}