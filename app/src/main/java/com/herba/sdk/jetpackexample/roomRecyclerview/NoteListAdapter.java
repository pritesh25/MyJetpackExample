package com.herba.sdk.jetpackexample.roomRecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Note> list;

    public NoteListAdapter(Context context,List<Note> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context    = context;
        this.list       =   list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder)
        {
            MyViewHolder holder1 = (MyViewHolder)holder;
            holder1.textView.setText(list.get(position).getmNote());
            holder1.textView2.setText(list.get(position).getId());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotes(List<Note> notes)
    {
        list = notes;
        notifyDataSetChanged();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView2;
        int position;

        public MyViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
            textView2 = view.findViewById(R.id.textView2);
        }
    }
}