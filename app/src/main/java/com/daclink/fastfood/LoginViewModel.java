package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.FoodRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private LiveData<List<User>> userByIDLiveData;
    private LiveData<List<User>> userByNameLiveData;

    private MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> userIDLiveData = new MutableLiveData<>();

    public LoginViewModel(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
        userByNameLiveData = Transformations.switchMap(userNameLiveData, name -> foodRepository.findUserByName(name));
    }

    public void findUserByID(String userID) {
        userByIDLiveData = foodRepository.findUserByID(userID);
    }

    public void findUserByName(String name) {
        userByNameLiveData = foodRepository.findUserByName(name);
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
