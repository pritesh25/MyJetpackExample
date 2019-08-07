package com.herba.sdk.jetpackexample.paging;

import android.content.IntentFilter;
import android.os.Bundle;

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

    RecyclerView pagingRecyclerView;
    ItemBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paging);

        receiver = new ItemBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.example.Broadcast");
        registerReceiver(receiver, filter);

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
}