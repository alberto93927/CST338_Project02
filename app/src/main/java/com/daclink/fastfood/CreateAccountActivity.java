package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;

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
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserRepository userRepository = new UserRepository(getApplication());
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel(userRepository);

        sharedPreferences = getSharedPreferences("fast_food_user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.EnterUserNameEditText.getText().toString();
                Log.d("Username", "User: " + username);
                String password = binding.EnterPasswordEditText.getText().toString();
                String type = "user";
                createAccountViewModel.createUser(username, password, type);

                Intent intent = IntentFactory.newLandingPageIntent(CreateAccountActivity.this);

                editor.putString("username", username);
                editor.putBoolean("LoggedInStatus", true);
                //editor.putInt("id", user.getId());
                //editor.putBoolean("admin", user.isAdmin());
                editor.apply();

                startActivity(intent);
                finish();
            }
        });

        /*createAccountViewModel.getUserByNameLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //This is where we actually handle login using our LiveData
                if (users != null && !users.isEmpty()) {
                    User user = users.get(0);
                    String Password = binding.EnterPasswordEditText.getText().toString();
                    //Need to implement password checking
                    String username = user.getName();
                    Intent intent = IntentFactory.newLandingPageIntent(CreateAccountActivity.this);
                    editor.putString("username", user.getName());
                    editor.putBoolean("LoggedInStatus", true);
                    editor.putInt("id", user.getId());
                    editor.putBoolean("admin", user.isAdmin());
                    editor.apply();
                    Log.d("UserData", "User ID: " + user.getId() + ", Name: " + user.getName());
                    startActivity(intent);
                    finish();
                } else {
                    // User not found or list empty
                    Log.d("UserData", "User not found");
                }
            }
        });*/

    }
}