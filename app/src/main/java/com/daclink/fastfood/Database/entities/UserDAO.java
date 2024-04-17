package com.daclink.fastfood.Database.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Delete
    void delete(User user);

    @Query("Select * from " + UserDatabase.userTable + " ORDER BY name")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE from " + UserDatabase.userTable)
    void deleteAll();

    @Query("Select * from " + UserDatabase.userTable + " WHERE name  LIKE :search")
    public LiveData<List<User>> findUserByName(String search);

    @Query("Select * from " + UserDatabase.userTable + " WHERE id LIKE :search")
    public LiveData<List<User>> findUserByID(String search);
}
