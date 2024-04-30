package com.daclink.fastfood;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;
import com.daclink.fastfood.databinding.ActivityLandingPageBinding;


public class LandingPage extends AppCompatActivity {

    private ActivityLandingPageBinding binding;
    private SharedPreferences sharedPreferences;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository = new UserRepository(getApplication());
        SharedPreferencesHelper helper = new SharedPreferencesHelper(LandingPage.this);
        User user = helper.getUser();

        binding.usernameTextView.setText(user.getName());
        boolean isAdmin = user.isAdmin();

        if(isAdmin){
            binding.AdminButton.setVisibility(View.VISIBLE);
        }
        else{
            binding.DeleteButton.setVisibility(View.VISIBLE);
        }

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.logout();

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

        binding.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LandingPage.this).setTitle("Confirm Delete").setMessage("Are you sure you want to delete your account?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton){


                        userRepository.deleteUser(user);
                        Toast.makeText(LandingPage.this, "Account Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

    }
}