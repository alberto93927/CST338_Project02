package com.daclink.fastfood;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class BrowseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Check if the fragment container exists in the layout
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // Create a new instance of RecyclerViewFragment
            ProductListFragment productListFragment = new ProductListFragment();
            ButtonFragment buttonFragment = new ButtonFragment();

            // Begin a transaction to replace the fragment container with RecyclerViewFragment
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, productListFragment, "RECYCLER_VIEW_FRAGMENT");
            fragmentTransaction.add(R.id.button_container, buttonFragment, "BUTTON_FRAGMENT");
            fragmentTransaction.commit();
        }
    }
}