package com.example.myapplication.ui.createHike;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateHikeViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CreateHikeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is create hike fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}