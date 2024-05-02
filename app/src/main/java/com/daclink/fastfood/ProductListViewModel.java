package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.ProductRepository;
import com.daclink.fastfood.Database.entities.UserRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private LiveData<List<Product>> productList;

    public ProductListViewModel(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }

    public LiveData<List<Product>> getProductList() {
        if (productList == null) {
            productList = productRepository.getAllProducts();
        }
        return productList;
    }

    public void addToCart(Product product) {
        userRepository.addToCart(product);
    }

    public void addToCart(Product product, int quantity) {
        userRepository.addToCart(product, quantity);
    }
}
