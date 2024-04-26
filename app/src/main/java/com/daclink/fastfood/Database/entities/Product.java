package com.daclink.fastfood.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FoodDatabase.productTable)

public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private double weight;

    public Product(String name, String description, double price, int quantity, double weight) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
