package com.daclink.fastfood;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.daclink.fastfood.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private AdminProductListViewModel adminProductListViewModel;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(new ProductAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);


//        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        adminProductListViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AdminProductListViewModel.class);
        adminProductListViewModel.getProductList().observe(this, products -> {
            adapter.submitList(products);
        });

//        binding.AddProductButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                s
//            }
//        });

    }
}