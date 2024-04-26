package com.daclink.fastfood.Database.entities;

import java.util.List;

public class Cart {

    private int cartID;
    private List<Integer> productIDs;

    public Cart(int cartID) {
        this.cartID = cartID;
    }

    public Cart(int cartID, List<Integer> productIDs) {
        this.cartID = cartID;
        this.productIDs = productIDs;
    }

    public void addProduct(int productID) {
        if(!productIDs.contains(productID)) {
            productIDs.add(productID);
        }
    }

    public void removeProduct(int productID) {
        if(productIDs.contains(productID)) {
            productIDs.remove(productID);
        }
    }

    public boolean hasProduct(int productID) {
        return productIDs.contains(productID);
    }

    public List<Integer> getProductIDs() {
        return productIDs;
    }
}
