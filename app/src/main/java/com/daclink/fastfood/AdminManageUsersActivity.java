package com.daclink.fastfood;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.databinding.ActivityAdminManageUsersBinding;

public class AdminManageUsersActivity extends AppCompatActivity {

    private ActivityAdminManageUsersBinding binding;
    private AdminUserListViewModel adminUserListViewModel;
    private AdminUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminManageUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = binding.usersRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdminUserAdapter(this, new AdminUserAdapter.UserDiff());
        recyclerView.setAdapter(adapter);

        adminUserListViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AdminUserListViewModel.class);
        adminUserListViewModel.getUserList().observe(this, users -> {
            adapter.submitList(users);
        });

        binding.addUserButton.setOnClickListener(v -> openAddUserDialog());


    }

    private void openAddUserDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.admin_dialog_add_user, null);
        builder.setView(view);

        EditText userName = view.findViewById(R.id.editTextUserName);
        EditText userPassword = view.findViewById(R.id.editTextUserPassword);
        EditText userType = view.findViewById(R.id.editTextUserType);

        Button addButton = view.findViewById(R.id.adminButtonAddUser);

        AlertDialog dialog = builder.create();
        addButton.setOnClickListener(v -> {
            User newUser = new User(
                    userName.getText().toString(),
                    userPassword.getText().toString(),
                    userType.getText().toString()
            );
            adminUserListViewModel.insert(newUser);
            dialog.dismiss();
        });

        dialog.show();

    }

    public void confirmRemoveUser(User user){
        new android.app.AlertDialog.Builder(this)
                .setTitle("Confirm Removal")
                .setMessage("Are you sure you want to remove this user?")
                .setPositiveButton("Remove", ((dialog, which) -> adminUserListViewModel.delete(user)))
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void openUpdateUserDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.admin_dialog_add_user, null);
        builder.setView(dialogView);

        EditText userName = dialogView.findViewById(R.id.editTextUserName);
        EditText userPassword = dialogView.findViewById(R.id.editTextUserPassword);
        EditText userType = dialogView.findViewById(R.id.editTextUserType);

        Button AddUserButton = dialogView.findViewById(R.id.adminButtonAddUser);

        userName.setText(user.getName());
        userPassword.setText(user.getPassword());
        userType.setText(user.getType());


        AddUserButton.setVisibility(View.INVISIBLE);

        builder.setPositiveButton("Update", (dialog, which) -> {
            user.setName(userName.getText().toString());
            user.setPassword(userPassword.getText().toString());
            user.setType(userType.getText().toString());

            adminUserListViewModel.update(user);
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}