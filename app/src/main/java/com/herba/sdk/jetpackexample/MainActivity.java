package com.herba.sdk.jetpackexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.herba.sdk.jetpackexample.livedata.MainLiveDataActivity;
import com.herba.sdk.jetpackexample.livedatawithviewmodel.MainLiveDataViewModelActivity;
import com.herba.sdk.jetpackexample.navigation.BottomNavigationActivity;
import com.herba.sdk.jetpackexample.navigation.SimpleNavigationActivity;
import com.herba.sdk.jetpackexample.recyclerview.RecyclerViewActivity;
import com.herba.sdk.jetpackexample.roomRecyclerview.MainRoomActivity;
import com.herba.sdk.jetpackexample.viewmodel.MainViewModelActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = "tag";//this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate Owner");

        getLifecycle().addObserver(new MyLifecycleObserver());

        findViewById(R.id.btnViewModel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainViewModelActivity.class));
            }
        });


        findViewById(R.id.btnLiveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainLiveDataActivity.class));
            }
        });

        findViewById(R.id.btnLiveDataViewModel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainLiveDataViewModelActivity.class));
            }
        });

        findViewById(R.id.btnRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainRoomActivity.class));
            }
        });

        findViewById(R.id.btnNavigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SimpleNavigationActivity.class));
            }
        });

        findViewById(R.id.btnBottomNavigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
            }
        });

        findViewById(R.id.btnRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Owner");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Owner");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Owner");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Owner");
    }
}
