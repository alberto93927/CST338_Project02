package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodRepository {

    private userDAO userDAO;
    private productDAO productDAO;
    private LiveData<List<User>> allUsers;

    public FoodRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.userDAO = db.foodDAO();
        this.productDAO = db.productDAO();
        this.allUsers = this.userDAO.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(User... user) {
        FoodDatabase.databaseWriterExecutor.execute(()->
                {
                    userDAO.insertUser(user);
                }
        );
    }

    public LiveData<List<User>> findUserByID(String userId) {
        return userDAO.findUserByID(userId);
    }

    public LiveData<List<User>> findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

    public LiveData<List<Product>> searchProductByName(String name) {
        return productDAO.searchProductByName(name);
    }
}
