package com.daclink.fastfood;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daclink.fastfood.Database.entities.Product;
import com.google.gson.Gson;

public class ProductFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private ProductViewModel mViewModel;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private TextView quantityTextView;
    private TextView weightTextView;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();
        Product product = gson.fromJson(getArguments().getString("KEY_PRODUCT"), Product.class);
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        nameTextView = view.findViewById(R.id.product_name);
        descriptionTextView = view.findViewById(R.id.product_description);
        priceTextView = view.findViewById(R.id.product_price);
        quantityTextView = view.findViewById(R.id.product_quantity);
        weightTextView = view.findViewById(R.id.product_weight);
        nameTextView.setText(product.getName());
        descriptionTextView.setText(product.getDescription());
        priceTextView.setText("Price: $" + String.format("%.2f", product.getPrice()));
        quantityTextView.setText(String.valueOf("Stock: " + product.getQuantity()));
        weightTextView.setText(String.valueOf("Weight: " + product.getWeight() + " lbs"));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Product");
    }

}