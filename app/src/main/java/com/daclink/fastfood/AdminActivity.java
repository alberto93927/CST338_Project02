package com.daclink.fastfood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.daclink.fastfood.Database.entities.Product;
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

        adminProductListViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AdminProductListViewModel.class);
        adminProductListViewModel.getProductList().observe(this, products -> {
            adapter.submitList(products);
        });

        binding.AddProductButton.setOnClickListener(v -> openAddProductDialog());

    }

    private void openAddProductDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.admin_dialog_add_product, null);
        builder.setView(view);

        EditText productName = view.findViewById(R.id.editTextProductName);
        EditText productDescription = view.findViewById(R.id.editTextProductDescription);
        EditText productPrice = view.findViewById(R.id.editTextProductPrice);
        EditText productQuantity = view.findViewById(R.id.editTextProductQuantity);
        EditText productWeight = view.findViewById(R.id.editTextProductWeight);
        Button addButton = view.findViewById(R.id.buttonAddProduct);

        AlertDialog dialog = builder.create();
        addButton.setOnClickListener(v -> {
            Product newProduct = new Product(
                    productName.getText().toString(),
                    productDescription.getText().toString(),
                    Double.parseDouble(productPrice.getText().toString()),
                    Integer.parseInt(productQuantity.getText().toString()),
                    Double.parseDouble(productWeight.getText().toString())
            );
            adminProductListViewModel.insert(newProduct);
            dialog.dismiss(); // Close dialog after adding product
        });

        dialog.show();

    }
}