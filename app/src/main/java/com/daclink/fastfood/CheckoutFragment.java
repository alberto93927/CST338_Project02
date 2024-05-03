package com.daclink.fastfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;
import com.google.android.material.resources.TextAppearanceConfig;

import java.util.HashMap;
import java.util.List;

public class CheckoutFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private SharedPreferencesHelper helper;
    private User user;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SharedPreferencesHelper(getContext());
        user = helper.getUser();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Checkout");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        List<Product> productList = helper.getProductList();
        Double total = calculateTotal(productList);
        int totalQuantity = getTotalItemCount();
        Log.d("ProductListSize", String.valueOf(helper.getProductList().size()));
        Log.d("Total", String.valueOf(total));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        TextView totalView = view.findViewById(R.id.text_total);
        totalView.setText("Total: $" + String.valueOf(total));

        TextView totalQuantityView = view.findViewById(R.id.text_total_quantity);
        totalQuantityView.setText("Total # of items : " + totalQuantity);

        CheckoutAdapter adapter = new CheckoutAdapter(productList, user.getCart().getProductIDs());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public Double calculateTotal(List<Product> productList) {
        double total = 0;
        HashMap<Integer, Integer> cartItems = user.getCart().getProductIDs();

        for(Product product : productList) {
            Log.d("product", product.getName());
            if(cartItems.containsKey(product.getId())) {
                total = total + product.getPrice() * cartItems.get(product.getId());
            }
        }
        return total;
    }

    public int getTotalItemCount() {
        int totalQuantity = 0;
        for(int quantity : user.getCart().getProductIDs().values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }
}

