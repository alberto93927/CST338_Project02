package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodRepository {

    private FoodDAO foodDAO;
    private LiveData<List<User>> allUsers;

    public FoodRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.foodDAO = db.foodDAO();
        this.allUsers = this.foodDAO.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(User... user) {
        FoodDatabase.databaseWriterExecutor.execute(()->
                {
                    foodDAO.insert(user);
                }
        );
    }

    public LiveData<List<User>> findUserByID(String userId) {
        return foodDAO.findUserByID(userId);
    }

    public LiveData<List<User>> findUserByName(String name) {
        return foodDAO.findUserByName(name);
    }
}
