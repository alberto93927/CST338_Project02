package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.databinding.ActivityLandingPageBinding;


public class LandingPage extends AppCompatActivity {

    private ActivityLandingPageBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferencesHelper helper = new SharedPreferencesHelper(LandingPage.this);
        User user = helper.getUser();

        binding.usernameTextView.setText(user.getName());
        boolean isAdmin = user.isAdmin();

        if(isAdmin){
            binding.AdminButton.setVisibility(View.VISIBLE);
        }


        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = IntentFactory.newMainActivityIntent(LandingPage.this);
                startActivity(intent);
                finish();
            }
        });

        binding.BrowseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = IntentFactory.newBrowseActivityIntent(LandingPage.this);
                startActivity(intent);
                finish();
            }
        });

        binding.ShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = IntentFactory.newShoppingCartActivityIntent(LandingPage.this);
                startActivity(intent);
                finish();
            }
        });

    }
}