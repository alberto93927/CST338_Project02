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
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UserRepository userRepository = new UserRepository(getApplication());
        LoginViewModel loginViewModel = new LoginViewModel(userRepository);
        sharedPreferences = getSharedPreferences("fast_food_user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.UsernameEditText.getText().toString();
                Log.d("Username", "User: " + username);
                loginViewModel.setUserName(username);

            }
        });

        loginViewModel.getUserByNameLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //This is where we actually handle login using our LiveData
                if (users != null && !users.isEmpty()) {
                    User user = users.get(0);
                    String Password = binding.PasswordEditText.getText().toString();
                    //Need to implement password checking
                    Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                    intent.putExtra("USERNAME_KEY", user.getName());
                    editor.putString("username", user.getName());
                    editor.putBoolean("LoggedInStatus", true);
                    editor.putInt("id", user.getId());
                    editor.putBoolean("admin", user.isAdmin());
                    editor.apply();
                    Log.d("UserData", "User ID: " + user.getId() + ", Name: " + user.getName());
                    startActivity(intent);
                } else {
                    // User not found or list empty
                    Log.d("UserData", "User not found");
                }
            }
        });

    }
}