package com.daclink.fastfood.Database.entities;

import java.util.HashMap;

public class Cart {

    private int cartID;
    private HashMap<Integer, Integer> productIDs;

    public Cart(int cartID) {
        this.cartID = cartID;
        productIDs = new HashMap<>();
    }

    public Cart(int cartID, HashMap<Integer, Integer> productIDs) {
        this.cartID = cartID;
        this.productIDs = productIDs;
    }

    public void addProduct(int productID) {
        if(!productIDs.containsKey(productID)) {
            productIDs.put(productID, 1);
        } else {
            productIDs.put(productID, productIDs.get(productID) + 1);
        }
    }

    public void removeProduct(int productID) {
        if(productIDs.containsKey(productID)) {
            productIDs.remove(productID);
        }
    }

    public void reduceProduct(int productID) {
        if(productIDs.containsKey(productID)) {
            if(productIDs.get(productID) > 1) {
                productIDs.put(productID, productIDs.get(productID) - 1);
            } else {
                removeProduct(productID);
            }
        }
    }

    public int getProductQuantity(int productID) {
        if(productIDs.containsKey(productID)) {
            return productIDs.get(productID);
        }
        return 0;
    }

    public HashMap<Integer, Integer> getProductIDs() {
        return productIDs;
    }
}
