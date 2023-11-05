package com.example.myapplication.ui.listHike;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListHikeViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListHikeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("HI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}