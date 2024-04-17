package com.daclink.fastfood;

import android.app.Application;
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

    //Not being used yet. Will be implemented soon.
    //SharedPreferences settings = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    //SharedPreferences.Editor editor = settings.edit();

    

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
                loginViewModel.setUserName(username);
                String Password = binding.PasswordEditText.getText().toString();
                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                intent.putExtra("USERNAME_KEY", username);
                Log.d("User", "Username:" + username);
                startActivity(intent);
            }
        });

        loginViewModel.getUserByNameLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Handle the list of users here
                if (users != null && !users.isEmpty()) {
                    User user = users.get(0);
                    // Do something with the user data
                    Log.d("UserData", "User ID: " + user.getId() + ", Name: " + user.getName());
                } else {
                    // User not found or list empty
                    Log.d("UserData", "User not found");
                }
            }
        });

    }


    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}