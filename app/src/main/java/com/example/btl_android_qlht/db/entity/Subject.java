package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subject")
public class Subject {
    @NonNull
    @PrimaryKey
    public String id;
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "credits")
    public int credits;

    @ColumnInfo(name = "price_per_credit")
    public double pricePerCredit;


    public Subject(String id, String name, int credits, double pricePerCredit) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.pricePerCredit = pricePerCredit;
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getPricePerCredit() {
        return pricePerCredit;
    }

    public void setPricePerCredit(double pricePerCredit) {
        this.pricePerCredit = pricePerCredit;
    }
    @Ignore
    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", pricePerCredit=" + pricePerCredit +
                '}';
    }
}
