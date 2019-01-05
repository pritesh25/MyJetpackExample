package com.herba.sdk.jetpackexample.roomRecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.roomRecyclerview.utils.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.herba.sdk.jetpackexample.roomRecyclerview.MainRoomActivity.UPDATE_NOTE_ACTIVITY_RESULT_CODE;

public class NoteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Note> list;

    public onDeleteClick onDeleteClick;

    public interface  onDeleteClick{
        void OnDeleteClick(Note note);
    }

    public NoteListAdapter(Context context, List<Note> list,onDeleteClick onDeleteClick) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.onDeleteClick = onDeleteClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.textView.setText(list.get(position).getmNote());
            holder1.textView2.setText(list.get(position).getId());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotes(List<Note> notes) {
        list = notes;
        notifyDataSetChanged();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView, textView2;
        ImageView imgDelete, imgEdit;

        public MyViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
            textView2 = view.findViewById(R.id.textView2);

            imgDelete = view.findViewById(R.id.imgDelete);
            imgEdit = view.findViewById(R.id.imgEdit);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onDeleteClick != null)
                    {
                        onDeleteClick.OnDeleteClick(list.get(getAdapterPosition()));
                    }
                    else
                    {
                        Log.d(TAG,"callback is null");
                    }
                }
            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d(TAG, "id = " + list.get(getAdapterPosition()).getId());

                    Intent intent = new Intent(context, NoteEditActivity.class);
                    intent.putExtra("db_note_id", list.get(getAdapterPosition()).getId());
                    ((Activity) context).startActivityForResult(intent, UPDATE_NOTE_ACTIVITY_RESULT_CODE);
                }
            });
        }
    }
}