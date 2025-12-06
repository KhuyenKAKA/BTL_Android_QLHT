package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Student")
public class Student {
    @NonNull
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

    public Student(@NonNull String id, String name, String className, String dob, String faculty, String major, String address, String phoneNumber, String email, int gender, String CID) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.dob = dob;
        this.faculty = faculty;
        this.major = major;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.CID = CID;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }
    @Ignore
    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", dob='" + dob + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", CID='" + CID + '\'' +
                '}';
    }
}
