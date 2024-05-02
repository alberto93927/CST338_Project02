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


/**
 * A fragment representing a list of Items.
 */
public class CartFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private SharedViewModel sharedViewModel;
    private CartViewModel viewModel;
    Cart cart;
    User user;

    private CartViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CartFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CartFragment newInstance(int columnCount) {
        CartFragment fragment = new CartFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getContext());
        user = helper.getUser();
        cart = user.getCart();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.init(new ProductRepository(requireActivity().getApplication()), user.getCart());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Cart");

        Log.d("cartcontents", String.valueOf(cart.getProductIDs()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);

        // Set up RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartViewAdapter(user);
        recyclerView.setAdapter(adapter);

        // Observe data changes
        viewModel.getProductList().observe(getViewLifecycleOwner(), productList -> adapter.setProducts(productList));

        return view;
    }


}