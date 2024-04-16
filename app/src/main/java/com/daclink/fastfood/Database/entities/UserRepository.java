package com.daclink.fastfood.Database.entities;

import android.app.Application;

import java.util.ArrayList;

public class UserRepository {

    private UserDAO userDAO;
    private ArrayList<User> allUsers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.allUsers = this.userDAO.getAllUsers();
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void addUser(User user) {
        UserDatabase.databaseWriterExecutor.execute(()->
                {
                    userDAO.insert(user);
                }
        );
    }
}
