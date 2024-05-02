package com.daclink.fastfood;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.ProductRepository;

import java.util.List;

public class AdminProductListViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<Product>> productList;

//    public AdminProductListViewModel(ProductRepository repository){
//        this.productRepository = repository;
//        productList = productRepository.getAllProducts();
//    }

    public AdminProductListViewModel(Application application){
        super(application);
        productRepository = new ProductRepository(application);
        productList = productRepository.getAllProducts();
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public void insert(Product product){
        productRepository.insert(product);
    }

    public void update(Product product){
        productRepository.delete(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }
}
