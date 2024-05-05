package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.Order;
import com.daclink.fastfood.Database.entities.OrderRepository;

import java.util.List;

public class OrderHistoryViewModel extends ViewModel {
    private MutableLiveData<List<Order>> itemsLiveData = new MutableLiveData<>();
    private OrderRepository orderRepository;

    public void init(OrderRepository repository, int userID) {
        orderRepository = repository;
        orderRepository.getOrderByUserID(userID).observeForever(orders -> itemsLiveData.postValue(orders));;
    }
    public LiveData<List<Order>> getItemsLiveData() {
        return itemsLiveData;
    }

    public void updateItems(List<Order> updatedItems) {
        itemsLiveData.setValue(updatedItems);
    }
}