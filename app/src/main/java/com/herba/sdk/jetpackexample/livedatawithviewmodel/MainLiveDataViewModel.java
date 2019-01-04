package com.herba.sdk.jetpackexample.livedatawithviewmodel;

import android.util.Log;

import java.util.Random;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainLiveDataViewModel extends ViewModel {


    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<String> data;

    public MutableLiveData<String> getNumber()
    {
        if(data == null)
        {
            data = new MutableLiveData<>();
            createNumber();
        }
        return data;
    }

    public void createNumber() {
        Log.d(TAG,"create number");
        Random random = new Random();
        data.setValue("Number = "+random.nextInt(10-1));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG,"onCleared called");
    }
}
