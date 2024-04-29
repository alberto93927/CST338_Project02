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

    private List<Integer> productIDs;

    @TypeConverters({Converters.class})
    private LocalDateTime date;

    public Order(int userID, List<Integer> productIDs, LocalDateTime date) {
        this.userID = userID;
        this.productIDs = productIDs;
        this.date = date;
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

    public List<Integer> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<Integer> productIDs) {
        this.productIDs = productIDs;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
