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

import com.daclink.fastfood.Database.entities.ProductRepository;
import com.daclink.fastfood.Database.entities.UserRepository;

public class ProductListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private ProductListViewModel productListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productListAdapter = new ProductListAdapter(null);
        recyclerView.setAdapter(productListAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductRepository productRepository = new ProductRepository(requireActivity().getApplication());
        ProductListViewModel productListViewModel = new ProductListViewModel(productRepository);
        productListViewModel.getProductList().observe(getViewLifecycleOwner(), newData -> {
            // Update UI or adapter with new data
            productListAdapter.setData(newData);
        });
    }
}
