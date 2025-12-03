package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Semester")
public class Semester {
    @NonNull
    @PrimaryKey
    public String id; // ví dụ: "20241"

    @ColumnInfo(name = "name")
    public String name;


    public Semester(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Ignore
    public Semester() {
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}