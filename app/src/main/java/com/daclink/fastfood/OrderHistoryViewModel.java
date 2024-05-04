package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.daclink.fastfood.Database.entities.Order;

import java.util.List;

public class OrderHistoryViewModel extends ViewModel {
    private MutableLiveData<List<Order>> itemsLiveData = new MutableLiveData<>();

    // Expose the LiveData to observe changes
    public LiveData<List<Order>> getItemsLiveData() {
        return itemsLiveData;
    }

    // You can update the data as needed (e.g., from a network request)
    public void updateItems(List<Order> updatedItems) {
        itemsLiveData.setValue(updatedItems);
    }
}