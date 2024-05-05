package com.daclink.fastfood;

import android.util.Pair;

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

    private LiveData<List<User>> userByCredentialsLiveData;


    private MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> userIDLiveData = new MutableLiveData<>();

    private MutableLiveData<Pair<String, String>> userCredentialsLiveData = new MutableLiveData<>();

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        userByCredentialsLiveData = Transformations.switchMap(userCredentialsLiveData, credentials -> userRepository.findUserByCredentials(credentials));
    }

    public LiveData<List<User>> getUserByCredentialsLiveData() {
        return userByCredentialsLiveData;
    }

    public void findUserByID(String userID) {
        userByIDLiveData = userRepository.findUserByID(userID);
    }

    public void findUserByName(String name) {
        userByNameLiveData = userRepository.findUserByName(name);
    }

    public void setUserCredentialsLiveData(String name, String pass) {
        userCredentialsLiveData.setValue(new Pair<>(name, pass));
    }
}
