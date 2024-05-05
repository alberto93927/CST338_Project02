package com.daclink.fastfood;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daclink.fastfood.Database.entities.Product;
import com.daclink.fastfood.Database.entities.User;

import java.util.HashMap;
import java.util.List;

public class CheckoutFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private SharedPreferencesHelper helper;
    private User user;

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        TextView subTotalView = view.findViewById(R.id.text_subtotal);
        subTotalView.setText("Subtotal: $" + String.format("%.2f", total));

        TextView totalView = view.findViewById(R.id.text_total);
        totalView.setText("Total w/ Tax: $" + String.format("%.2f", total * 1.1));

        TextView totalQuantityView = view.findViewById(R.id.text_total_quantity);
        totalQuantityView.setText("Total # of items : " + totalQuantity);

        Button checkoutButton = view.findViewById(R.id.button_checkout);
        checkoutButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_QUANTITY", totalQuantity);
            bundle.putDouble("KEY_TOTAL", total * 1.1);
            AppCompatActivity activity = (AppCompatActivity) unwrap(v.getContext());
            ConfirmationFragment confirmationFragment = new ConfirmationFragment();
            confirmationFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, confirmationFragment).addToBackStack(null).commit();
        });

        CheckoutAdapter adapter = new CheckoutAdapter(productList, user.getCart().getProductIDs());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public Double calculateTotal(List<Product> productList) {
        double total = 0;
        HashMap<Integer,Integer> cartItems = new HashMap<>();
        cartItems.putAll(user.getCart().getProductIDs());
        //known bug - under certain conditions this will crash checkout
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

    private static Activity unwrap(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        return (Activity) context;
    }
}

