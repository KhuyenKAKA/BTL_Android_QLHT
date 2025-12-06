package com.example.btl_android_qlht.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btl_android_qlht.db.entity.Student;
import com.example.btl_android_qlht.db.dto.StudentProfileDTO;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);
    @Query("SELECT " +
            "s.id, s.name, s.class_name, s.faculty, s.dob, s.gender, s.email, s.phone_number, s.address, " +
            "w.balance, w.debt " +
            "FROM Student s " +
            "LEFT JOIN Wallet w ON s.id = w.studentId " +
            "WHERE s.id = :studentId")
    StudentProfileDTO getStudentProfile(String studentId);

    @Query("UPDATE Student SET dob = :dob, email = :email, phone_number = :phone, address = :address WHERE id = :id")
    void updateContactInfo(String id, String dob, String email, String phone, String address);
}