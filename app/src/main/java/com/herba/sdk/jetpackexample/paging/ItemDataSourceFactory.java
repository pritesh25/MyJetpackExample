package com.herba.sdk.jetpackexample.paging;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.herba.sdk.jetpackexample.paging.model.Item;

public class ItemDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

    private Context context;

    public ItemDataSourceFactory(Context context) {
        this.context = context;
    }

    @Override
    public DataSource<Integer, Item> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource(context);

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}