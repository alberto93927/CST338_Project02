package com.daclink.fastfood;

import android.content.Context;
import android.content.SharedPreferences;

import com.daclink.fastfood.Database.entities.User;
import com.google.gson.Gson;

public class SharedPreferencesHelper {

    private static final String PREF_NAME = "MyPreferences";
    private static final String KEY_USER = "user";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(User user) {
        String userJson = gson.toJson(user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, userJson);
        editor.apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson != null) {
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }
}