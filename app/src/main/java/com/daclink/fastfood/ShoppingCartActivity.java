package com.daclink.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.fastfood.databinding.ActivityShoppingCartBinding;


public class ShoppingCartActivity extends AppCompatActivity {

    private ActivityShoppingCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ProceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = IntentFactory.newCheckoutPageActivityIntent(ShoppingCartActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }
}