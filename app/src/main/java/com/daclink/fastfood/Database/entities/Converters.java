package com.daclink.fastfood.Database.entities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class Converters {

    static Gson gson = new Gson();

    @TypeConverter
    public static String fromCart(Cart cart) {
        return gson.toJson(cart);
    }

    @TypeConverter
    public static Cart fromString(String cart) {
        return gson.fromJson(cart, Cart.class);
    }
}
