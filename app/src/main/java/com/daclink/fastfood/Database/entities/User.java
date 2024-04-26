package com.daclink.fastfood.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FoodDatabase.userTable)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String password;
    private String type;

    public User(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
        //Might need to add userID to constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdmin(boolean admin) {
        if(admin) {
            type = "admin";
        } else {
            type = "user";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAdmin() {
        if(type.equals("admin")) {
            return true;
        }
        return false;
    }
}
