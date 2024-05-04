package com.daclink.fastfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.Order;
import com.daclink.fastfood.Database.entities.Product;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    private List<Order> items;

    public OrderHistoryAdapter(List<Order> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_item, parent, false);
        return new OrderHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        // Bind data to views (e.g., TextViews, ImageViews) in your ViewHolder
        Order currentItem = items.get(position);
        // Set data to views here...
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setData(List<Order> newData) {
        items = newData;
        notifyDataSetChanged();
    }

    static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
