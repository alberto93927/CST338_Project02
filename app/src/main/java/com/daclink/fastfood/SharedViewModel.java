package com.daclink.fastfood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> currentFragmentTag = new MutableLiveData<>();

    public void setCurrentFragmentTag(String tag) {
        currentFragmentTag.setValue(tag);
    }

    public LiveData<String> getCurrentFragmentTag() {
        return currentFragmentTag;
    }
}