package com.herba.sdk.jetpackexample.paging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ItemBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String data = intent.getStringExtra("msg");
        Log.d("Received data : ", data);
        Toast.makeText(context, "s = " + data, Toast.LENGTH_SHORT).show();
    }
}
