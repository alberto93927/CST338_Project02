package com.daclink.fastfood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.fastfood.Database.entities.OrderRepository;
import com.daclink.fastfood.Database.entities.User;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    private OrderHistoryViewModel viewModel;
    private SharedPreferencesHelper helper;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OrderHistoryAdapter adapter = new OrderHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        helper = new SharedPreferencesHelper(this);
        user = helper.getUser();

        viewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        OrderRepository orderRepository = new OrderRepository(getApplication());
        viewModel.init(orderRepository, user.getId());
        viewModel.getItemsLiveData().observe(this, items -> {
            // Update the adapter with new data
            adapter.setData(items);
        });
    }
}