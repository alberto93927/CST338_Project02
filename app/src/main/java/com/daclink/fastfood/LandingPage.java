package com.daclink.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing_page);

        Intent intent = getIntent();

        String username = intent.getStringExtra("USERNAME_KEY");
        Log.d("USERNAME", "User " + username);
        //String password = intent.getStringExtra("PASSWORD_KEY");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username);

    }
}