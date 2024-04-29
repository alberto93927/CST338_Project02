package com.daclink.fastfood.Database.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface orderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrder(Order... orders);

    @Delete
    void deleteOrder(Order order);

    @Query("DELETE from " + FoodDatabase.orderTable)
    void deleteAll();

    @Query("Select * from " + FoodDatabase.orderTable + " ORDER BY id")
    LiveData<List<Order>> getAllOrders();

    @Query("Select * from " + FoodDatabase.orderTable + " WHERE id LIKE search")
    LiveData<List<Product>> searchOrderByID(int search);

    @Query("Select * from " + FoodDatabase.orderTable + " WHERE userID LIKE search")
    LiveData<List<Product>> searchOrderByUserID(int search);
}
