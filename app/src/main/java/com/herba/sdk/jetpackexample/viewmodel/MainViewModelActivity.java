package com.herba.sdk.jetpackexample.viewmodel;

import android.os.Bundle;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainViewModelActivity extends AppCompatActivity {

    private TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewmodel);

        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);//view modal example
        //MainModel model = new MainModel();//normal model class example

        tvNumber = findViewById(R.id.tvNumber);
        tvNumber.setText(model.getNumber());

    }
}
