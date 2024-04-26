package com.daclink.fastfood.Database.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
    public LiveData<List<User>> findUserByName(String search);

    @Query("Select * from " + FoodDatabase.userTable + " WHERE id LIKE :search")
    public LiveData<List<User>> findUserByID(String search);
}
