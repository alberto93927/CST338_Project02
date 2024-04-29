package com.daclink.fastfood.Database.entities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Converters {

    static Gson gson = new Gson();

    @TypeConverter
    public static LocalDateTime fromTimestamp(Long value) {
        return value == null ? null : LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC);
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDateTime date) {
        return date == null ? null : date.toEpochSecond(ZoneOffset.UTC);
    }

    @TypeConverter
    public static String fromCart(Cart cart) {
        return gson.toJson(cart);
    }

    @TypeConverter
    public static Cart fromString(String cart) {
        return gson.fromJson(cart, Cart.class);
    }
}
