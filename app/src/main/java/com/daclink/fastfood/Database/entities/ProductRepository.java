package com.daclink.fastfood.Database.entities;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<Product> findProductByID(int id) {
        return productDAO.findProductByID(id);
    }


    public LiveData<List<Product>> findProductsByCart(Cart cart) {
        Set<Integer> set = cart.getProductIDs().keySet();
        List<Integer> list = new ArrayList<>(set);
        return productDAO.findProductsByCart(list);
    }

}
