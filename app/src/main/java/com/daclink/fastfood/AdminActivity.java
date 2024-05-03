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
        adapter = new ProductAdapter(this, new ProductAdapter.ProductDiff());
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
            dialog.dismiss();
        });

        dialog.show();

    }

    public void confirmRemoveProduct(Product product){
        new android.app.AlertDialog.Builder(this)
                .setTitle("Confirm Removal")
                .setMessage("Are you sure you want to remove this product?")
                .setPositiveButton("Remove", ((dialog, which) -> adminProductListViewModel.delete(product)))
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void openUpdateProductDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.admin_dialog_add_product, null);
        builder.setView(dialogView);

        EditText productName = dialogView.findViewById(R.id.editTextProductName);
        EditText productDescription = dialogView.findViewById(R.id.editTextProductDescription);
        EditText productPrice = dialogView.findViewById(R.id.editTextProductPrice);
        EditText productQuantity = dialogView.findViewById(R.id.editTextProductQuantity);
        EditText productWeight = dialogView.findViewById(R.id.editTextProductWeight);
        Button AddProductButton = dialogView.findViewById(R.id.buttonAddProduct);

        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.valueOf(product.getPrice()));
        productQuantity.setText(String.valueOf(product.getQuantity()));
        productWeight.setText(String.valueOf(product.getWeight()));

        AddProductButton.setVisibility(View.INVISIBLE);

        builder.setPositiveButton("Update", (dialog, which) -> {
            product.setName(productName.getText().toString());
            product.setDescription(productDescription.getText().toString());
            product.setPrice(Double.parseDouble(productPrice.getText().toString()));
            product.setQuantity(Integer.parseInt(productQuantity.getText().toString()));
            product.setWeight(Double.parseDouble(productWeight.getText().toString()));

            adminProductListViewModel.update(product);
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}