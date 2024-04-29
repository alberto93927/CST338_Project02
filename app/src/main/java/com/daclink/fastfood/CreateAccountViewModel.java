package com.daclink.fastfood;

import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;

public class CreateAccountViewModel extends ViewModel {
    private UserRepository userRepository;
    public CreateAccountViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password, String type){
       userRepository.createUser(username, password, type);
    }
}
