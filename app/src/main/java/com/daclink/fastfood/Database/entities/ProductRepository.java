package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ProductRepository {

    private productDAO productDAO;
    //private  LiveData<List<Product>> allProducts;
    //this.allProducts = productDAO.getAllProducts();

    public ProductRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.productDAO = db.productDAO();
    }

    public LiveData<List<Product>> searchProductByName(String name) {
        return productDAO.searchProductByName(name);
    }

    public void insert(Product product) {
        FoodDatabase.databaseWriterExecutor.execute(() -> productDAO.insertProduct(product));
    }

    public void delete(Product product) {
        FoodDatabase.databaseWriterExecutor.execute(() -> productDAO.deleteProduct(product));
    }
    public void update(Product product) {
        FoodDatabase.databaseWriterExecutor.execute(() -> productDAO.updateProduct(product));
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
