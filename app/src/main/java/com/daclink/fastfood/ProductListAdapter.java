package com.daclink.fastfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.Product;

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
        holder.bind(product);
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

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            priceTextView = itemView.findViewById(R.id.text_view_price);
            quantityTextView = itemView.findViewById(R.id.text_view_quantity);
            weightTextView = itemView.findViewById(R.id.text_view_weight);
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
