package com.daclink.fastfood.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;
import java.util.List;

@Entity(tableName = FoodDatabase.orderTable)
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userID;

    @TypeConverters({Converters.class})
    private Cart products;

    @TypeConverters({Converters.class})
    private LocalDateTime date;

    public Order(int userID, Cart products, LocalDateTime date) {
        this.userID = userID;
        this.products = products;
        this.date = date;
    }

    public Cart getProducts() {
        return products;
    }

    public void setProducts(Cart products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
