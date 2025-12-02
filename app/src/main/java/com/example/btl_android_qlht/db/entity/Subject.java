package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subject")
public class Subject {
    @PrimaryKey
    public String id;
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "credits")
    public int credits;

    @ColumnInfo(name = "price_per_credit")
    public double pricePerCredit;

    public Subject() {}
}
