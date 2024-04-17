package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    String Username = "";
    String Password = "";

    //Not being used yet. Will be implemented soon.
    SharedPreferences settings = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Username = binding.UsernameEditText.getText().toString();
        Password = binding.PasswordEditText.getText().toString();

        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                startActivity(intent);
            }
        });

    }


    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}