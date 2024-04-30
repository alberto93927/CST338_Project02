package com.daclink.fastfood;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        sharedPreferences = getSharedPreferences("fast_food_user_info", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "No Name");
        binding.usernameTextView.setText(username);
        boolean isAdmin = sharedPreferences.getBoolean("admin", false);

        if(isAdmin){
            binding.AdminButton.setVisibility(View.VISIBLE);
        }
        else{
            binding.DeleteButton.setVisibility(View.VISIBLE);
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


                        //userRepository.deleteUser();
                        Toast.makeText(LandingPage.this, "Account Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

    }
}