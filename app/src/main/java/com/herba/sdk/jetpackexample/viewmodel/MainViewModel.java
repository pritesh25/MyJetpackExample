package com.herba.sdk.jetpackexample.viewmodel;

import android.util.Log;

import java.util.Random;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final String TAG = this.getClass().getSimpleName();
    private String myRandomNumber;

    public String getNumber()
    {
        Log.i(TAG,"Get Number");
        if(myRandomNumber == null)
        {
            createNumber();
        }
        return myRandomNumber;
    }

    public void createNumber() {

        Log.i(TAG,"createNumber");
        Random random = new Random();
        myRandomNumber ="Number :"+(random.nextInt(10-1));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"viewmodel onCleared called");
    }
}