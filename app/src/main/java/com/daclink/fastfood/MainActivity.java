package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.Database.entities.UserRepository;
import com.daclink.fastfood.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Acting as a key to retrieve userID
    private static final String MAIN_ACTIVITY_USER_ID = "com.daclink.fastfood.MAIN_ACTIVITY_USER_ID";
    ActivityMainBinding binding;
    private UserRepository repository;

    public static final String TAG = "AL_USERLOG";

    int loggedInUserId = -1;

    private static final String LOGGED_IN_KEY = "is";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        boolean isUserLoggedIn = settings.getBoolean("isTheUserLoggedIn", false);

        if(isUserLoggedIn == true){
            Intent intent = new Intent(MainActivity.this, LandingPage.class);
            startActivity(intent);
        }
        else{

            /*
            loginUser();
            if(loggedInUserId == -1){
                Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
                startActivity(intent);
            }
            */

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            binding.CreateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //not implemented yet
                    createUser();
                }
            });
        }

    }

    private void loginOptions(){

    }

    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

    private void loginUser(){
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }

    private void createUser(){

    }
}