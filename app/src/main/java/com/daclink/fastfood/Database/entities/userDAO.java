package com.daclink.fastfood.Database.entities;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface userDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from " + FoodDatabase.userTable + " ORDER BY name")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE from " + FoodDatabase.userTable)
    void deleteAll();

    @Query("Select * from " + FoodDatabase.userTable + " WHERE name  LIKE :search")
    LiveData<List<User>> findUserByName(String search);

    @Query("Select * from " + FoodDatabase.userTable + " WHERE id LIKE :search")
    LiveData<List<User>> findUserByID(String search);

    @Query("Select * from " + FoodDatabase.userTable + " WHERE name = :name AND password = :password")
    LiveData<List<User>> findUserByCredentials(String name, String password);

    @Query("UPDATE " + FoodDatabase.userTable + " SET name = :newName, password = :newPassword WHERE name = :currentName")
    void updateUserByUsername(String currentName, String newName, String newPassword);

    @Update
    void updateUser(User user);

}
