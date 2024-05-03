package com.daclink.fastfood;

import static androidx.core.content.res.TypedArrayUtils.getString;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.fastfood.Database.entities.Cart;
import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private final SharedPreferencesHelper helper;
    private User user;

    public CartViewAdapter(User user, SharedPreferencesHelper helper) {
        this.helper = helper;
        this.user = user;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
        helper.saveProductList(productList);
        notifyDataSetChanged();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("user", user.getName());
        Log.d("cartcontents2", String.valueOf(user.getCart().getProductIDs()));
        Product product = productList.get(position);
        holder.mIdView.setText(product.getName());
        holder.mContentView.setText(product.getDescription());
        holder.quantityView.setText(String.valueOf(user.getCart().getProductQuantity(product.getId())));
        holder.remainingQuantityView.setText(product.getQuantity() + " in stock");
        holder.plusButton.setOnClickListener(v -> {
            int quantity = Integer.valueOf(holder.quantityView.getText().toString());
            if(quantity < product.getQuantity()) {
                quantity++;
                holder.quantityView.setText(String.valueOf(quantity));
                user.getCart().addProduct(product.getId());
                helper.saveUser(user);
            } else {
                Context context = v.getContext();
                Toast.makeText(context, product.getName() + " has no more stock", Toast.LENGTH_SHORT).show();
            }

        });
        holder.minusButton.setOnClickListener(v -> {
            int quantity = Integer.valueOf(holder.quantityView.getText().toString());
            if(quantity > 1) {
                quantity--;
                holder.quantityView.setText(String.valueOf(quantity));
                user.getCart().reduceProduct(product.getId());
                helper.saveUser(user);
            } else {
                //cart returns when returning to activity, need to look into it
                productList.remove(product);
                user.getCart().removeProduct(product.getId());
                helper.saveUser(user);
                setProducts(productList);
            }
        });
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.cart_list_product, parent, false));
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView quantityView;
        
        public final TextView remainingQuantityView;
        Button plusButton;
        Button minusButton;

        public ViewHolder(View view) {
            super(view);
            mIdView = itemView.findViewById(R.id.text_product_name);
            mContentView = itemView.findViewById(R.id.text_product_description);
            quantityView = itemView.findViewById(R.id.text_user_quantity);
            remainingQuantityView = itemView.findViewById(R.id.text_quantity_remaining);
            plusButton = itemView.findViewById(R.id.plus_button);
            minusButton = itemView.findViewById(R.id.minus_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}