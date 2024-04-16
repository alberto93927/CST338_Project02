package com.daclink.fastfood.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UserDatabase.userTable)
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

    public boolean isAdmin() {
        if(type.equals("admin")) {
            return true;
        }
        return false;
    }
}
