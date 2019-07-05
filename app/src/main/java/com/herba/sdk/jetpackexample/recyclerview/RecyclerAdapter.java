package com.herba.sdk.jetpackexample.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Data> list;

    public RecyclerAdapter(Context context,
                           ArrayList<Data> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RecyclerViewHolder) {

            ((RecyclerViewHolder) holder).tv.setText(list.get(position).getName());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotes(ArrayList<Data> recyclerViewModels) {
        list = recyclerViewModels;
        notifyDataSetChanged();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        RecyclerViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv);
        }
    }
}