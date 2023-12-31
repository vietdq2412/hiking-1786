package com.example.myapplication.ui.hikeDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HikeDetailViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public HikeDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hike detail");
    }

    public LiveData<String> getText() {
        return mText;
    }
}