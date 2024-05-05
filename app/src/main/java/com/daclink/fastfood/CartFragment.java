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

import com.daclink.fastfood.Database.entities.Cart;
import com.daclink.fastfood.Database.entities.ProductRepository;
import com.daclink.fastfood.Database.entities.User;

public class CartFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private CartViewModel viewModel;
    private SharedPreferencesHelper helper;
    private User user;
    private CartViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SharedPreferencesHelper(getContext());
        user = helper.getUser();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.init(new ProductRepository(requireActivity().getApplication()), user.getCart());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Cart");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);

        // Set up RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartViewAdapter(user, helper);
        recyclerView.setAdapter(adapter);

        // Observe data changes
        viewModel.getProductList().observe(getViewLifecycleOwner(), productList -> adapter.setProducts(productList));

        return view;
    }
}