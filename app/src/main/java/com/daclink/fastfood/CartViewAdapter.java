package com.daclink.fastfood;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private User user;

    public CartViewAdapter(User user) {
        this.user = user;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
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
        public final TextView quantityView;
        Button plusButton;
        Button minusButton;

        public ViewHolder(View view) {
            super(view);
            mIdView = itemView.findViewById(R.id.text_product_name);
            mContentView = itemView.findViewById(R.id.text_product_description);
            quantityView = itemView.findViewById(R.id.text_user_quantity);
            plusButton = itemView.findViewById(R.id.plus_button);
            minusButton = itemView.findViewById(R.id.minus_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}