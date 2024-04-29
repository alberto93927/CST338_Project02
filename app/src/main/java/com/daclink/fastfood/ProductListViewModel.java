package com.daclink.fastfood;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private ProductRepository productRepository;
    private MutableLiveData<List<Product>> productList;

    public ProductListViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<List<Product>> getProductList() {
        if (productList == null) {
            productList = productRepository.getAllProducts();
        }
        return productList;
    }
}
