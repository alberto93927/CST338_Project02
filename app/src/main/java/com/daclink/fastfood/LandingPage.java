package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.daclink.fastfood.databinding.ActivityLandingPageBinding;


public class LandingPage extends AppCompatActivity {

    private ActivityLandingPageBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("fast_food_user_info", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "No Name");
        binding.usernameTextView.setText(username);
        boolean isAdmin = sharedPreferences.getBoolean("admin", false);

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

    }
}