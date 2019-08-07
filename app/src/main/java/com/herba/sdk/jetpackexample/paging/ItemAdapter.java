package com.herba.sdk.jetpackexample.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.paging.model.Item;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.question_id == newItem.question_id;
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mCtx;

    ItemAdapter(Context cxt) {
        super(DIFF_CALLBACK);
        this.mCtx = cxt;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Item item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.owner.display_name);
            holder.pos.setText(String.valueOf(position + 1));
            Glide.with(mCtx)
                    .load(item.owner.profile_image)
                    .into(holder.imageView);
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView, pos;
        ImageView imageView;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            pos = itemView.findViewById(R.id.pos);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
