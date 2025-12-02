package com.example.btl_android_qlht.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * from user")
    List<User> getAllUsers();

}
