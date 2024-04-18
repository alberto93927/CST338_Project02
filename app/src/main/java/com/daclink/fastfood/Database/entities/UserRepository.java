package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.allUsers = this.userDAO.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(User... user) {
        UserDatabase.databaseWriterExecutor.execute(()->
                {
                    userDAO.insert(user);
                }
        );
    }

    public LiveData<List<User>> findUserByID(String userId) {
        return userDAO.findUserByID(userId);
    }

    public LiveData<List<User>> findUserByName(String name) {
        return userDAO.findUserByName(name);
    }
}
