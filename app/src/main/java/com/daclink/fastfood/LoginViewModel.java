package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userByIDLiveData;
    private LiveData<List<User>> userByNameLiveData;

    private MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> userIDLiveData = new MutableLiveData<>();

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        userByNameLiveData = Transformations.switchMap(userNameLiveData, name -> userRepository.findUserByName(name));
    }

    public void findUserByID(String userID) {
        userByIDLiveData = userRepository.findUserByID(userID);
    }

    public void findUserByName(String name) {
        userByNameLiveData = userRepository.findUserByName(name);
    }

    public LiveData<List<User>> getUserByIDLiveData() {
        return userByIDLiveData;
    }

    public LiveData<List<User>> getUserByNameLiveData() {
        return userByNameLiveData;
    }

    public void setUserId(String userId) {
        userIDLiveData.setValue(userId);
    }

    public void setUserName(String userId) {
        userNameLiveData.setValue(userId);
    }
}
