package com.herba.sdk.jetpackexample.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;

import java.util.ArrayList;

public class NavigationRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<String> arrayList = new ArrayList<>();

    public NavigationRecyclerAdapter(Context context) {
        this.context = context;

        for (int i = 0; i <= 50; i++) {
            arrayList.add("" + i);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation, parent, false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NavigationViewHolder) {
            ((NavigationViewHolder) holder).tv.setText(arrayList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private class NavigationViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public NavigationViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv);
        }
    }
}
