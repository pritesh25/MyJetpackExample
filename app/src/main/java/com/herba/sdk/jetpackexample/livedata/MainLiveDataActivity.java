package com.herba.sdk.jetpackexample.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class MainLiveDataActivity extends AppCompatActivity {

    private TextView tvNumber;
    private MainLiveData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_livedata);

        tvNumber = findViewById(R.id.tvNumber);

        model = new MainLiveData();
        LiveData<String> myRandomNumber = model.getNumber();//live data example
        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvNumber.setText(s);
            }
        });//it is observer to check change in the live data

        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createNumber();
            }
        });//generate new data , will automatically update ui

    }
}