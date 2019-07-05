package com.herba.sdk.jetpackexample.recyclerview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    ArrayList<Data> arrayList = new ArrayList<>();
    RecyclerAdapter adapter;
    RecyclerViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        adapter = new RecyclerAdapter(getApplicationContext(), new ArrayList<Data>());
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(RecyclerViewModel.class);

        MutableLiveData<ArrayList<Data>> data = noteViewModel.getListMutableLiveData();
        data.observe(this, new Observer<ArrayList<Data>>() {
            @Override
            public void onChanged(ArrayList<Data> recyclerViewModels) {
                adapter.setNotes(recyclerViewModels);
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteViewModel.setData(new Data("tempData"));
            }
        });
    }
}