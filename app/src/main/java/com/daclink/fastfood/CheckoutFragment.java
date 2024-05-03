package com.daclink.fastfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;

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
        Double total = calculateTotal(helper.getProductList());
        Log.d("Total", String.valueOf(total));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        return view;
    }

    public Double calculateTotal(List<Product> productList) {
        double total = 0;
        HashMap<Integer, Integer> cartItems = user.getCart().getProductIDs();
        for(Product product : productList) {
            if(cartItems.containsKey(product.getId())) {
                total = total + product.getPrice() * cartItems.get(product.getId());
            }
        }
        return total;
    }
}