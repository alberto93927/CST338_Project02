package com.daclink.fastfood.Database.entities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface productDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product... products);

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE from " + FoodDatabase.productTable)
    void deleteAll();

    @Query("Select * from " + FoodDatabase.productTable + " ORDER BY id")
    MutableLiveData<List<Product>> getAllProducts();

    @Query("Select * from " + FoodDatabase.productTable + " WHERE name LIKE :search")
    LiveData<List<Product>> searchProductByName(String search);
}
