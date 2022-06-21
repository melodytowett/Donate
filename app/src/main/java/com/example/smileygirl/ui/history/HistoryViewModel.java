package com.example.smileygirl.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hello");
    }
    // TODO: Implement the ViewModel
}