package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("fast_food_user_info", Context.MODE_PRIVATE);


        if(sharedPreferences.getBoolean("LoggedInStatus", false)){
            Intent intent = IntentFactory.newLandingPageIntent(MainActivity.this);
            startActivity(intent);
            finish();
        }
        else{

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = IntentFactory.newLoginActivityIntent(MainActivity.this);
                    startActivity(intent);
                    finish();
                }
            });

            binding.CreateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = IntentFactory.newCreateAccountActivityIntent(MainActivity.this);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

}