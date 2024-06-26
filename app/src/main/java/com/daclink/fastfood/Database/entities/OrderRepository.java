package com.daclink.fastfood.Database.entities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderRepository {

    private orderDAO orderDAO;

    public OrderRepository(Application application) {
        FoodDatabase db = FoodDatabase.getDatabase(application);
        this.orderDAO = db.orderDAO();
    }

    public LiveData<List<Order>> searchOrderByID(int id) {
        return orderDAO.searchOrderByID(id);
    }

    public LiveData<List<Order>> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public LiveData<List<Order>> getOrderByUserID(int userID) {
        return orderDAO.searchOrderByUserID(userID);
    }

    public void addOrder(Order order) {
        orderDAO.insertOrder(order);
    }
}
