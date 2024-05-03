package com.daclink.fastfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {

    private static final String PREF_NAME = "MyPreferences";
    private static final String KEY_USER = "user";

    private static final String KEY_PRODUCT_LIST = "product_list";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    Type listType;

    public SharedPreferencesHelper(Context context) {
        listType = new TypeToken<List<Product>>() {}.getType();
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(User user) {
        String userJson = gson.toJson(user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, userJson);
        editor.apply();
    }

    public void saveProductList(List<Product> list) {
        String productJson = gson.toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PRODUCT_LIST, productJson);
        editor.apply();
    }

    public void addToProductList(Product product) {
        List<Product> productList;
        if(sharedPreferences.getString(KEY_PRODUCT_LIST, null) == null){
            productList = new ArrayList<>();
        } else {
            productList = getProductList();
        }
        if(!inProductList(productList, product)) {
            Log.d("ProductList", "not in list");
            productList.add(product);
            saveProductList(productList);
        }
    }

    public boolean inProductList(List<Product> productList, Product product) {
        boolean inList = false;
        for(Product p : productList) {
            if(p.getId() == product.getId()) {
                inList = true;
            }
        }
        return inList;
    }

    public List<Product> getProductList() {
        String productJson = "";
        productJson = sharedPreferences.getString(KEY_PRODUCT_LIST, "");
        if(!productJson.equals("")) {
            return gson.fromJson(productJson, listType);
        }
        return null;
    }
    public User getUser() {

        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson != null) {
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
    }
}