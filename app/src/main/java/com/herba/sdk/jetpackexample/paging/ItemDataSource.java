package com.herba.sdk.jetpackexample.paging;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.paging.PageKeyedDataSource;

import com.herba.sdk.jetpackexample.paging.model.Item;
import com.herba.sdk.jetpackexample.paging.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    //the size of a page that we want
    static final int PAGE_SIZE = 10;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    //we need to fetch from stackoverflow
    private static final String SITE_NAME = "stackoverflow";
    private String tag = this.getClass().getSimpleName();

    private Context context;
    private LocalBroadcastManager localBroadcastManager;
    private Intent intent;

    ItemDataSource(Context context) {
        this.context = context;
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
        intent = new Intent("DATA");
    }

    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        if (response.body() != null) {

                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);

                        } else {
                            Toast.makeText(context, "Response is null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d(tag, "(onFailure) loadInitial" + t.getMessage());
                        //Toast.makeText(context, "(onFailure) loadInitial = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        intent.putExtra("msg", "(onFailure) loadInitial" + t.getMessage());
                        localBroadcastManager.sendBroadcast(intent);
                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key 
                            callback.onResult(response.body().items, adjacentKey);
                        } else {
                            Toast.makeText(context, "Response is null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d(tag, "(onFailure) loadBefore" + t.getMessage());
                        //Toast.makeText(context, "(onFailure) loadBefore = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        intent.putExtra("msg", "(onFailure) loadBefore" + t.getMessage());
                        localBroadcastManager.sendBroadcast(intent);
                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().has_more ? params.key + 1 : null;

                            //passing the loaded data and next page value 
                            callback.onResult(response.body().items, key);
                        } else {
                            Toast.makeText(context, "Response is null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d(tag, "(onFailure) loadAfter" + t.getMessage());
                        //Toast.makeText(context, "(onFailure) loadAfter = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        intent.putExtra("msg", "(onFailure) loadAfter" + t.getMessage());
                        localBroadcastManager.sendBroadcast(intent);
                    }
                });
    }
}