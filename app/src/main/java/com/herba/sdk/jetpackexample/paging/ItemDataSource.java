package com.herba.sdk.jetpackexample.paging;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.herba.sdk.jetpackexample.paging.model.Item;
import com.herba.sdk.jetpackexample.paging.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herba.sdk.jetpackexample.paging.MainPagingActivity.PROGRESS_ACTIONS;
import static com.herba.sdk.jetpackexample.paging.MainPagingActivity.PROGRESS_VISIBILITY;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    //the size of a page that we want
    static final int PAGE_SIZE = 100; //max size is 100

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    //we need to fetch from stackoverflow
    private static final String SITE_NAME = "stackoverflow";
    private Intent intent;

    private String tag = this.getClass().getSimpleName();
    private Context context;

    ItemDataSource(Context context) {
        this.context = context;
        intent = new Intent(PROGRESS_ACTIONS);
    }

    private void setShowProgressbar() {
        intent.putExtra(PROGRESS_VISIBILITY, true);
        context.sendBroadcast(intent);
    }

    private void setHideProgressbar() {
        intent.putExtra(PROGRESS_VISIBILITY, false);
        context.sendBroadcast(intent);
    }

    private void errorMessage(Response<StackApiResponse> response) {

        Log.d(tag, "(errorMessage) response = " + response.body());

        Toast.makeText(context, "Response is null", Toast.LENGTH_SHORT).show();

        /*try {
            //{"error_id":400,"error_message":"pagesize","error_name":"bad_parameter"}
            JSONObject object = new JSONObject(String.valueOf(response));
            object.getString("error_message");
            object.getString("error_name");

            Toast.makeText(context,""+object.getString("error_message"), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            Log.d(tag, "(errorMessage) catch error = " + e.getMessage());
        }*/
    }

    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        setShowProgressbar();

        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        setHideProgressbar();

                        if (response.body() != null) {
                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                        } else {
                            errorMessage(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        setHideProgressbar();
                        Log.d(tag, "(onFailure) loadInitial" + t.getMessage());
                        Toast.makeText(context, "(onFailure) loadInitial = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        setShowProgressbar();

        RetrofitClient.getInstance()
                .getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        setHideProgressbar();
                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            //passing the loaded data
                            //and the previous page key 
                            callback.onResult(response.body().items, adjacentKey);
                        } else {
                            errorMessage(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d(tag, "(onFailure) loadBefore" + t.getMessage());
                        Toast.makeText(context, "(onFailure) loadBefore = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        setHideProgressbar();
                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        setShowProgressbar();

        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        setHideProgressbar();

                        if (response.body() != null) {
                            //if the response has next page incrementing the next page number
                            Integer key = response.body().has_more ? params.key + 1 : null;

                            //passing the loaded data and next page value 
                            callback.onResult(response.body().items, key);
                        } else {
                            errorMessage(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d(tag, "(onFailure) loadAfter" + t.getMessage());
                        Toast.makeText(context, "(onFailure) loadAfter = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        setHideProgressbar();
                    }
                });
    }
}