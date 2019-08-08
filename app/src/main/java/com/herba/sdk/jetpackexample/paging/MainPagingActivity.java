package com.herba.sdk.jetpackexample.paging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.paging.model.Item;

public class MainPagingActivity extends AppCompatActivity {

    static final String PROGRESS_ACTIONS = "com.herba.sdk.PROGRESS_ACTIONS";
    static final String PROGRESS_VISIBILITY = "PROGRESS_VISIBILITY";
    private final String TAG = this.getClass().getSimpleName();
    RecyclerView pagingRecyclerView;

    ItemBroadcastReceiver receiver;
    IntentFilter filter;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paging);

        progress_circular = findViewById(R.id.progress_circular);

        receiver = new ItemBroadcastReceiver();
        filter = new IntentFilter(PROGRESS_ACTIONS);

        pagingRecyclerView = findViewById(R.id.pagingRecyclerView);
        pagingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pagingRecyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this);

        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        pagingRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class ItemBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == PROGRESS_ACTIONS) {

                try {
                    if (intent.getExtras().getBoolean(PROGRESS_VISIBILITY)) {

                        progress_circular.setVisibility(View.VISIBLE);

                    } else {

                        progress_circular.setVisibility(View.GONE);

                    }
                } catch (Exception e) {
                    Log.d(TAG, "(ItemBroadcastReceiver) catch error = " + e);
                }

                //display progress bar
                //Toast.makeText(context, "display progress bar", Toast.LENGTH_SHORT).show();

            } else {

                //hide progress bar
            }
        }
    }
}