package com.herba.sdk.jetpackexample.paging;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;

public class MainPagingActivity extends AppCompatActivity {

    RecyclerView pagingRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paging);

        pagingRecyclerView = findViewById(R.id.pagingRecyclerView);

    }
}