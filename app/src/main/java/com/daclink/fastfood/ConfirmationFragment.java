package com.daclink.fastfood;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.fastfood.Database.entities.Order;
import com.daclink.fastfood.Database.entities.User;

import java.time.LocalDateTime;

public class ConfirmationFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private SharedPreferencesHelper helper;
    private User user;
    private Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SharedPreferencesHelper(getContext());
        user = helper.getUser();
        order = new Order(user.getId(), user.getCart(), LocalDateTime.now(), 5, 5);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.setCurrentFragmentTag("Confirmation");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);


        return view;
    }

    // Call this method to start the AsyncTask
    public void startAsyncTask() {
        new OrderAsyncTask().execute();
    }

}

class OrderAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        // Perform your database operation here
        return null;
    }
}


