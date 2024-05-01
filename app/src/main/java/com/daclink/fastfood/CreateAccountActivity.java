package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daclink.fastfood.Database.entities.FoodDatabase;
import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.fastfood.databinding.ActivityCreateAccountBinding;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private ActivityCreateAccountBinding binding;
    private CreateAccountViewModel createAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserRepository userRepository = new UserRepository(getApplication());
        createAccountViewModel = new CreateAccountViewModel(userRepository);



        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.EnterUserNameEditText.getText().toString();
                Log.d("Username", "User: " + username);
                String password = binding.EnterPasswordEditText.getText().toString();
                String type = "user";

                createAccountViewModel.checkUsernameExists(username).observe(CreateAccountActivity.this, users -> {
                    if(users != null && !users.isEmpty()){
                        Toast.makeText(CreateAccountActivity.this, "Username already taken. Pick a different Username.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        createAccount(username, password, type);
                    }
                });

            }
        });

    }

    private void createAccount(String username, String password, String type){
        if(!username.isEmpty() && !password.isEmpty()){

            createAccountViewModel.createUser(username, password, type);
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

            SharedPreferencesHelper helper = new SharedPreferencesHelper(getApplicationContext());
            helper.saveUser(new User(username, password, type));

            Intent intent = IntentFactory.newLandingPageIntent(CreateAccountActivity.this);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
        }

    }
}