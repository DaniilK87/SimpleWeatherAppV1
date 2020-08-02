package com.example.simpleweatherappv1.ui.hourFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HourViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HourViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Погода по часам");
    }

    public LiveData<String> getText() {
        return mText;
    }
}