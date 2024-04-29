package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private userDAO userDAO;

    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.userDAO = db.foodDAO();
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

    public void createUser(String name, String password, String type){
        User newUser = new User(name, password, type);
        FoodDatabase.databaseWriterExecutor.execute(() -> userDAO.insertUser(newUser));
    }

    public LiveData<List<User>> findUserByID(String userId) {
        return userDAO.findUserByID(userId);
    }

    public LiveData<List<User>> findUserByName(String name) {
        return userDAO.findUserByName(name);
    }
}
