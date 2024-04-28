package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {

    private productDAO productDAO;

    public ProductRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.productDAO = db.productDAO();
    }

    public LiveData<List<Product>> searchProductByName(String name) {
        return productDAO.searchProductByName(name);
    }
}
