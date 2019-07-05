package com.herba.sdk.jetpackexample.recyclerview;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class RecyclerViewModel extends ViewModel {

    ArrayList<Data> model = new ArrayList<>();
    private String tag = this.getClass().getSimpleName();
    private MutableLiveData<ArrayList<Data>> listMutableLiveData;

    public MutableLiveData<ArrayList<Data>> getListMutableLiveData() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            //setData();
        }
        return listMutableLiveData;
    }

    void setData(Data tempData) {
        Log.d(tag, "create number");
        model.add(model.size(), tempData);
        listMutableLiveData.setValue(model);
    }

    void setData() {
        Log.d(tag, "create number");
        model.add(new Data("a"));
        listMutableLiveData.setValue(model);
    }

}
