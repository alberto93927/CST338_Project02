package com.daclink.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;

import com.daclink.fastfood.Database.entities.FoodDatabase;
import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.fastfood.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

    private ActivityCreateAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //UserRepository userRepository = new UserRepository(getApplication());
        CreateAccountViewModel createAccountViewModel = new ViewModelProvider(this).get(CreateAccountViewModel.class);

        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.EnterUserNameEditText.getText().toString();
                String password = binding.EnterPasswordEditText.getText().toString();
                String type = binding.AdminOrUserEditText.getText().toString();
                createAccountViewModel.createUser(name, password, type);
                Intent intent = IntentFactory.newLandingPageIntent(CreateAccountActivity.this);
                startActivity(intent);
                finish();
            }
        });

    }
}