package com.daclink.fastfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.R;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.productNameTextView.setText(product.getName());
        holder.productDescriptionTextView.setText(product.getDescription());
        // Add more bindings as needed
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productNameTextView;
        private final TextView productDescriptionTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productDescriptionTextView = itemView.findViewById(R.id.product_description);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<Product> {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    }
}
