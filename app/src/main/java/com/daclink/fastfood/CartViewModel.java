package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.daclink.fastfood.Database.entities.Cart;
import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.ProductRepository;
import java.util.List;

public class CartViewModel extends ViewModel {

    private ProductRepository productRepository;
    private MutableLiveData<List<Product>> productListLiveData = new MutableLiveData<>();
    private Cart cart;

    public void init(ProductRepository repository, Cart userCart) {
        productRepository = repository;
        cart = userCart;
        productRepository.findProductsByCart(cart).observeForever(products -> productListLiveData.postValue(products));
    }

    public LiveData<List<Product>> getProductList() {
        return productListLiveData;
    }

    private LiveData<List<Product>> getProducts(Cart cart) {
        return productRepository.findProductsByCart(cart);
    }
}
