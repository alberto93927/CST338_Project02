package com.daclink.fastfood;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private ProductListViewModel productListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productListAdapter = new ProductListAdapter();
        recyclerView.setAdapter(productListAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        productListViewModel.getProductList().observe(getViewLifecycleOwner(), newData -> {
            // Update UI or adapter with new data
          //  productListViewModel.;
        });
    }
}
