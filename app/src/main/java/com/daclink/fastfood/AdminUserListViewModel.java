package com.daclink.fastfood;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;

import java.util.List;

public class AdminUserListViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userList;

    public AdminUserListViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        userList = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public void insert(User user){
        userRepository.addUser(user);
    }

    public void update(User user){
        userRepository.updateUser(user);
    }

    public void delete(User user){
        userRepository.deleteUser(user);
    }

}
