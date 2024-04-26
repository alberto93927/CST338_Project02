package com.daclink.fastfood.Database.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface productDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProdct(Product... products);

    @Delete
    void deleteProduct(Product product);

    @Query("Select * from " + FoodDatabase.productTable)
    LiveData<List<User>> getAllProducts();
}
