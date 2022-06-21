package com.example.smileygirl.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> homeViewModel;

    public HomeViewModel() {
        homeViewModel = new MutableLiveData<>();
        homeViewModel.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return homeViewModel;
    }
}
    // TODO: Implement the ViewModel
