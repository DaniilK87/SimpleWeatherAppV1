package com.example.simpleweatherappv1.ui.cityFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CityViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public CityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Выбор города");
    }

    public LiveData<String> getText() {
        return mText;
    }
}