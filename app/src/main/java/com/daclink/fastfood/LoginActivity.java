package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;
import com.daclink.fastfood.databinding.ActivityLoginBinding;

import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UserRepository userRepository = new UserRepository(getApplication());
        LoginViewModel loginViewModel = new LoginViewModel(userRepository);
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.UsernameEditText.getText().toString();
                String password = binding.PasswordEditText.getText().toString();
                Log.d("Username", "User: " + username);
                loginViewModel.setUserCredentialsLiveData(username, password);
            }
        });

        loginViewModel.getUserByCredentialsLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //This is where we actually handle login using our LiveData
                if (users != null && !users.isEmpty()) {
                    User user = users.get(0);
                    String password = binding.PasswordEditText.getText().toString();
                    //Need to implement password checking
                    String username = user.getName();
                    //Intent intent = IntentFactory.newLandingPageIntent(LoginActivity.this, username);
                    Intent intent = IntentFactory.newLandingPageIntent(LoginActivity.this);
                    SharedPreferencesHelper helper = new SharedPreferencesHelper(LoginActivity.this);
                    Log.d("UserData", "User ID: " + user.getId() + ", Name: " + user.getName());
                    helper.saveUser(user);
                    startActivity(intent);
                    finish();
                } else {
                    // User not found or list empty
                    Log.d("UserData", "User not found");
                }
            }
        });

    }
}