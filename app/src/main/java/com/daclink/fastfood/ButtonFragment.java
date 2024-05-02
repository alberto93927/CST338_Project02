package com.daclink.fastfood;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ButtonFragment extends Fragment {

    Button button1;
    Button button2;
    private SharedViewModel sharedViewModel;

    public ButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        button1 = view.findViewById(R.id.cart_button);
        button2 = view.findViewById(R.id.checkout_button);

        // Set onClick listeners for each button
        button1.setOnClickListener(v -> {
            // Handle button1 click
            AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
            CartFragment cartFragment = new CartFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cartFragment).addToBackStack(null).commit();
        });

        button2.setOnClickListener(v -> {
            // Handle button2 click
            AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
            CheckoutFragment checkoutFragment = new CheckoutFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, checkoutFragment).addToBackStack(null).commit();
        });
        observeCurrentFragment();
        return view;
    }

    private static Activity unwrap(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        return (Activity) context;
    }

    // Observe changes in the current fragment
    // This could probably be implemented in a more modular way but time is a finite resourced
    private void observeCurrentFragment() {
        sharedViewModel.getCurrentFragmentTag().observe(getViewLifecycleOwner(), tag -> {
            // Update button behavior in the second container based on the current fragment
            switch (tag) {
                case "Browse":
                    Log.d("FragmentTag", "Browse");
                    button1.setText(R.string.view_cart);
                    button1.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        CartFragment cartFragment = new CartFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cartFragment).addToBackStack(null).commit();
                    });

                    button2.setText(R.string.checkout);
                    button2.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        CheckoutFragment checkoutFragment = new CheckoutFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, checkoutFragment).addToBackStack(null).commit();
                    });
                    break;
                case "Cart":
                    Log.d("FragmentTag", "Cart");
                    button1.setText(R.string.browse);
                    button1.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        ProductListFragment productListFragment = new ProductListFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productListFragment).addToBackStack(null).commit();
                    });

                    button2.setText(R.string.checkout);
                    button2.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        CheckoutFragment checkoutFragment = new CheckoutFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, checkoutFragment).addToBackStack(null).commit();
                    });
                    break;
                case "Checkout":
                    Log.d("FragmentTag", "Checkout");
                    button1.setText(R.string.browse);
                    button1.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        ProductListFragment productListFragment = new ProductListFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productListFragment).addToBackStack(null).commit();
                    });

                    button2.setText(R.string.view_cart);
                    button2.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        CartFragment cartFragment = new CartFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cartFragment).addToBackStack(null).commit();
                    });
                    break;
                case "Product":
                    Log.d("FragmentTag", "Product");
                    button1.setText(R.string.browse);
                    button1.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        ProductListFragment productListFragment = new ProductListFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productListFragment).addToBackStack(null).commit();
                    });

                    button2.setText(R.string.view_cart);
                    button2.setOnClickListener(v -> {
                        AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
                        CartFragment cartFragment = new CartFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cartFragment).addToBackStack(null).commit();
                    });
                    break;
            }
        });
    }
}