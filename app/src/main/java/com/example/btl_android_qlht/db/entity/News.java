package com.example.btl_android_qlht.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "News")
public class News {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "time")
    public String time;

    public News() {}
}
