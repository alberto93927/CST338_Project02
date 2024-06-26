package com.daclink.fastfood;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;
import com.google.gson.Gson;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Product> productList;
    private final User user;
    private final SharedPreferencesHelper helper;

    public ProductListAdapter(List<Product> productList, User user, SharedPreferencesHelper helper) {
        this.productList = productList;
        this.user = user;
        this.helper = helper;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Product product = productList.get(position);
        Gson gson = new Gson();
        holder.bind(product);

        holder.viewDetailsButton.setOnClickListener(v -> {
            // Notify the listener of the button click
            Bundle bundle = new Bundle();
            String productJson = gson.toJson(product);
            bundle.putString("KEY_PRODUCT", productJson);

            AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
            ProductFragment productFragment = new ProductFragment();
            productFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productFragment).addToBackStack(null).commit();
        });

        holder.addToCartButton.setOnClickListener(v -> {
            // Notify the listener of the button click
            Context context = v.getContext();
            if(user.getCart().getProductQuantity(product.getId()) < product.getQuantity()) {
                user.addToCart(product.getId());
                Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                helper.saveUser(user);
                helper.addToProductList(product);
            } else {
                Toast.makeText(context, product.getName() + " has no more stock", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Activity unwrap(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) context;
    }

    public void setData(List<Product> newData) {
        productList = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView priceTextView;
        private TextView quantityTextView;

        Button addToCartButton;
        Button viewDetailsButton;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            priceTextView = itemView.findViewById(R.id.text_view_price);
            quantityTextView = itemView.findViewById(R.id.text_view_quantity);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }

        public void bind(Product product) {
            //temp
            nameTextView.setText(product.getName());
            priceTextView.setText("$" + String.format("%.2f", product.getPrice()));
            quantityTextView.setText("Stock: " + String.valueOf(product.getQuantity()));
        }
    }
}
