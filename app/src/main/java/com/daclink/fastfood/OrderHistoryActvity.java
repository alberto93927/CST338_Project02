package com.daclink.fastfood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    private OrderHistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OrderHistoryAdapter adapter = new OrderHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        viewModel.getItemsLiveData().observe(this, items -> {
            // Update the adapter with new data
            adapter.setItems(items);
        });

        // Fetch data (e.g., from a repository or API) and update the ViewModel
        // viewModel.updateItems(newItems);
    }
}