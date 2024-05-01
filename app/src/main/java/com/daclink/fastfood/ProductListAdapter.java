package com.daclink.fastfood;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.Product;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Product> productList;

    public ProductListAdapter(List<Product> productList) {
        this.productList = productList;
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
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notify the listener of the button click
                Log.d("Product", "clucked");
                Bundle bundle = new Bundle();
                String productJson = gson.toJson(product);
                bundle.putString("KEY_PRODUCT", productJson);

                AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                ProductFragment productFragment = new ProductFragment();
                productFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productFragment).addToBackStack(null).commit();
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
        private TextView descriptionTextView;
        private TextView priceTextView;
        private TextView quantityTextView;
        private TextView weightTextView;

        Button button;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            priceTextView = itemView.findViewById(R.id.text_view_price);
            quantityTextView = itemView.findViewById(R.id.text_view_quantity);
            weightTextView = itemView.findViewById(R.id.text_view_weight);
            button = itemView.findViewById(R.id.add_to_cart_button);
        }

        public void bind(Product product) {
            //temp
            nameTextView.setText(product.getName());
            descriptionTextView.setText(product.getDescription());
            priceTextView.setText(String.valueOf(product.getPrice()));
            quantityTextView.setText(String.valueOf(product.getQuantity()));
            weightTextView.setText(String.valueOf(product.getWeight()));
        }

    }
}
