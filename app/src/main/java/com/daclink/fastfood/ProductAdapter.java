package com.daclink.fastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.daclink.fastfood.Database.entities.Product;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {


    private Context context;

    public ProductAdapter(Context context, @NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_product_item, parent, false);
        return new ProductViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.productNameTextView.setText(product.getName());
        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productPriceTextView.setText(String.valueOf(product.getPrice()));
        holder.productQuantityTextView.setText(String.valueOf(product.getQuantity()));
        holder.productWeightTextView.setText(String.valueOf(product.getWeight()));
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productNameTextView;
        private final TextView productDescriptionTextView;
        private final TextView productPriceTextView;
        private final TextView productQuantityTextView;
        private final TextView productWeightTextView;

        private final Button deleteButton;
        private final Button upDateButton;
        private final ProductAdapter adapter;


        public ProductViewHolder(View itemView, ProductAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            productNameTextView = itemView.findViewById(R.id.product_name);
            productDescriptionTextView = itemView.findViewById(R.id.product_description);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productQuantityTextView = itemView.findViewById(R.id.product_quantity);
            productWeightTextView = itemView.findViewById(R.id.product_weight);
            deleteButton = itemView.findViewById(R.id.AdminRemoveItemButton);
            upDateButton = itemView.findViewById(R.id.AdminUpdateItemButton);

            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = adapter.getItem(position);
                    if (itemView.getContext() instanceof AdminActivity) {
                        ((AdminActivity) itemView.getContext()).confirmRemoveProduct(product);
                    }
                }
            });

            upDateButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product productToUpdate = adapter.getItem(position);
                    ((AdminActivity) itemView.getContext()).openUpdateProductDialog(productToUpdate);
                }
            });
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
