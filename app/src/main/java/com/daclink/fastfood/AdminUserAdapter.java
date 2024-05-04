package com.daclink.fastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.User;

public class AdminUserAdapter extends ListAdapter<User, AdminUserAdapter.UserViewHolder> {

    private Context context;

    public AdminUserAdapter(Context context, @NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_user_remove_update, parent, false);
        return new UserViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);
        holder.userNameTextView.setText(user.getName());
        holder.passwordTextView.setText(user.getPassword());
        holder.userTypeTextView.setText(user.getType());
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userNameTextView;
        private final TextView passwordTextView;
        private final TextView userTypeTextView;

        private final Button deleteButton;
        private final Button upDateButton;
        private final AdminUserAdapter adapter;


        public UserViewHolder(View itemView, AdminUserAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            userNameTextView = itemView.findViewById(R.id.userName);
            passwordTextView = itemView.findViewById(R.id.userPassword);
            userTypeTextView = itemView.findViewById(R.id.userType);

            deleteButton = itemView.findViewById(R.id.AdminDeleteUserButton);
            upDateButton = itemView.findViewById(R.id.AdminUpdateUserButton);

            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    User user = adapter.getItem(position);
                    if (itemView.getContext() instanceof AdminManageUsersActivity) {
                        ((AdminManageUsersActivity) itemView.getContext()).confirmRemoveUser(user);
                    }
                }
            });

            upDateButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    User userToUpdate = adapter.getItem(position);
                    ((AdminManageUsersActivity) itemView.getContext()).openUpdateUserDialog(userToUpdate);

                }
            });

        }

    }

    static class UserDiff extends DiffUtil.ItemCallback<User> {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    }

}
