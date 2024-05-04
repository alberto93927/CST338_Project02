package com.daclink.fastfood;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.UserRepository;
import com.daclink.fastfood.databinding.ActivityAccountSettingsBinding;


public class AccountSettingsActivity extends AppCompatActivity {


    private ActivityAccountSettingsBinding binding;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private UserRepository userRepository;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userRepository = new UserRepository(getApplication());
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        loadCurrentUser();

        binding.buttonUpdateSettings.setOnClickListener(v -> updateUserSettings());
    }

    private void loadCurrentUser() {
        currentUser = sharedPreferencesHelper.getUser();
        editTextUsername.setText(currentUser.getName());
        editTextPassword.setText(currentUser.getPassword());
    }

    private void updateUserSettings() {
        String currentUsername = currentUser.getName(); // The username before changes
        String newUsername = editTextUsername.getText().toString().trim();
        String newPassword = editTextPassword.getText().toString().trim();

        if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
            userRepository.updateUserByUsername(currentUsername, newUsername, newPassword);
            currentUser.setName(newUsername);
            currentUser.setPassword(newPassword);
            sharedPreferencesHelper.saveUser(currentUser);

            Toast.makeText(this, "User updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username and password cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }


}
