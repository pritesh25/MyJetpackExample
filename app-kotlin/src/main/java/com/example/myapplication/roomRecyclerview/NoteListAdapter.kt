package com.example.myapplication.roomRecyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.roomRecyclerview.MainRoomActivity.Companion.UPDATE_NOTE_ACTIVITY_RESULT_CODE
import com.example.myapplication.roomRecyclerview.utils.Note

class NoteListAdapter(private val context: Context,
                      private var list: List<Note>,
                      var noteAdapterCallback: NoteAdapterCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    private val TAG = this::class.java.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MyViewHolder) {
            val holder1 = holder as MyViewHolder
            holder1.textView.setText(list!![position].getNote())
            holder1.textView2.setText(list!![position].id)

        }
    }

    fun setNotes(notes: List<Note>) {
        list = notes
        notifyDataSetChanged()
    }

    private inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var textView: TextView
        internal var textView2: TextView
        internal var imgDelete: ImageView
        internal var imgEdit: ImageView

        init {

            textView = view.findViewById(R.id.textView)
            textView2 = view.findViewById(R.id.textView2)

            imgDelete = view.findViewById(R.id.imgDelete)
            imgEdit = view.findViewById(R.id.imgEdit)

            imgDelete.setOnClickListener {
                if (noteAdapterCallback!= null) {
                    noteAdapterCallback.OnDeleteItemClick(list!![adapterPosition])
                } else {
                    Log.d(TAG, "callback is null")
                }
            }

            imgEdit.setOnClickListener {
                Log.d(TAG, "id = " + list!![adapterPosition].id)

                val intent = Intent(context, NoteEditActivity::class.java)
                intent.putExtra("db_note_id", list!![adapterPosition].id)
                (context as Activity).startActivityForResult(intent, UPDATE_NOTE_ACTIVITY_RESULT_CODE)
            }
        }
    }
}