package com.daclink.fastfood.Database.entities;

import android.app.Application;
import android.util.Pair;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private final userDAO userDAO;

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
                userDAO.insertUser(user)
        );
    }

    public void createUser(String name, String password, String type){
        User newUser = new User(name, password, type);
        FoodDatabase.databaseWriterExecutor.execute(() -> userDAO.insertUser(newUser));
    }

    public void deleteUser(User user) {
        FoodDatabase.databaseWriterExecutor.execute(() -> userDAO.deleteUser(user));
    }

    public void updateUserByUsername(String currentName, String newName, String newPassword) {
        FoodDatabase.databaseWriterExecutor.execute(() ->
                userDAO.updateUserByUsername(currentName, newName, newPassword)
        );
    }

    public void updateUser(User user) {
        FoodDatabase.databaseWriterExecutor.execute(() -> userDAO.updateUser(user));
    }

    public void addToCart(Product product) {

    }

    public void addToCart(Product product, int quantity) {

    }

    public LiveData<List<User>> findUserByID(String userId) {
        return userDAO.findUserByID(userId);
    }

    public LiveData<List<User>> findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

    public LiveData<List<User>> findUserByCredentials(Pair<String, String> credentials) {
        return userDAO.findUserByCredentials(credentials.first, credentials.second);
    }
}
