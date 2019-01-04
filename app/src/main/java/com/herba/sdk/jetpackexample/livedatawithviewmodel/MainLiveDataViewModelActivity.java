package com.herba.sdk.jetpackexample.livedatawithviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainLiveDataViewModelActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private TextView tvNumber;
    private MainLiveDataViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_livedata_viewmodel);

        tvNumber = findViewById(R.id.tvNumber);

        model = ViewModelProviders.of(this).get(MainLiveDataViewModel.class);//view modal example
        LiveData<String> data = model.getNumber();
        data.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvNumber.setText(s);
            }
        });

        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createNumber();
            }
        });

    }




}
