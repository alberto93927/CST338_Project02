package com.daclink.fastfood;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.Product;

import java.util.HashMap;
import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    private List<Product> productList;
    private HashMap <Integer, Integer> cartContents;

    public CheckoutAdapter(List<Product> dataList, HashMap <Integer, Integer> cartContents) {
        this.cartContents = cartContents;
        this.productList = dataList;
    }

    // Create ViewHolder for each item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.quantityView.setText("Quantity: " +cartContents.get(product.getId()));
        holder.priceView.setText("$" + String.format("%.2f", product.getPrice()));
        holder.bindData(product);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView quantityView;
        private TextView priceView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            quantityView = itemView.findViewById(R.id.text_user_quantity);
            priceView = itemView.findViewById(R.id.text_price);
        }

        public void bindData(Product product) {
            textView.setText(product.getName());
        }
    }
}
