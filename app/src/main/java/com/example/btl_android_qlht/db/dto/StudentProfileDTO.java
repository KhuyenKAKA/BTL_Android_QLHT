package com.example.btl_android_qlht.db.dto;

import androidx.room.ColumnInfo;

public class StudentProfileDTO {
    // --- Thông tin từ bảng Student ---
    @ColumnInfo(name = "id")
    public String mssv;

    @ColumnInfo(name = "name")
    public String fullName;

    @ColumnInfo(name = "class_name")
    public String className;

    @ColumnInfo(name = "faculty")
    public String faculty;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "gender")
    public int genderCode; // 1: Nam, 0: Nữ (Lấy từ DB ra)

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phonenumber")
    public String phoneNumber;

    @ColumnInfo(name = "address")
    public String address;

    // --- Thông tin từ bảng Wallet (Ví tiền) ---
    @ColumnInfo(name = "balance")
    public double walletBalance;

    @ColumnInfo(name = "debt")
    public double walletDebt;

    // Hàm tiện ích để hiển thị giới tính ra giao diện
    public String getGenderText() {
        return (genderCode == 1) ? "Nam" : "Nữ";
    }
}