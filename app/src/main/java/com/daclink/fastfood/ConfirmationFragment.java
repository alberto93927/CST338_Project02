package com.daclink.fastfood;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.fastfood.Database.entities.Order;
import com.daclink.fastfood.Database.entities.OrderRepository;
import com.daclink.fastfood.Database.entities.User;

import java.time.LocalDateTime;

public class ConfirmationFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private SharedPreferencesHelper helper;
    private User user;
    private Order order;

    private int quantity;
    private double total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SharedPreferencesHelper(getContext());
        user = helper.getUser();
        Bundle args = getArguments();
        if (args != null) {
            quantity = args.getInt("KEY_QUANTITY");
            total = args.getDouble("KEY_TOTAL");
            // Use the data as needed
        }
        order = new Order(user.getId(), user.getCart(), LocalDateTime.now(), total, quantity);
        OrderRepository orderRepository = new OrderRepository(requireActivity().getApplication());
        OrderAsyncTask addOrderTask = new OrderAsyncTask(getContext(), orderRepository, order);
        addOrderTask.execute();
        user.clearCart();
        helper.saveUser(user);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Confirmation");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        Button orderHistoryButton = view.findViewById(R.id.view_order_history_button);

        orderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = IntentFactory.newOrderHistoryIntent(getContext());
                startActivity(intent);
            }
        });

        return view;
    }


}

class OrderAsyncTask extends AsyncTask<Void, Void, Void> {
    private OrderRepository orderRepository;
    private Order order;

    public OrderAsyncTask(Context context, OrderRepository orderRepository, Order order) {
        this.orderRepository = orderRepository;
        this.order = order;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        orderRepository.addOrder(order);
        return null;
    }
}


