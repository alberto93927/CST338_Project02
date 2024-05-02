package com.daclink.fastfood;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daclink.fastfood.Database.entities.Cart;
import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.ProductRepository;
import com.daclink.fastfood.placeholder.PlaceholderContent.PlaceholderItem;
import com.daclink.fastfood.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();

    public void setProducts(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.mIdView.setText(product.getName());
        holder.mContentView.setText(product.getDescription());
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Assuming you have a layout file named "list_item_product" for each item
        return new ViewHolder(inflater.inflate(R.layout.cart_list_product, parent, false));
        //return new ViewHolder(FragmentCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = itemView.findViewById(R.id.text_product_name);
            mContentView = itemView.findViewById(R.id.text_product_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}