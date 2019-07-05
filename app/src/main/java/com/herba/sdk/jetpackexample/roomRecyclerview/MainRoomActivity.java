package com.herba.sdk.jetpackexample.roomRecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.roomRecyclerview.utils.Note;
import com.herba.sdk.jetpackexample.roomRecyclerview.utils.NoteViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.herba.sdk.jetpackexample.roomRecyclerview.NoteAddActivity.MYDATA;
import static com.herba.sdk.jetpackexample.roomRecyclerview.NoteEditActivity.NOTE_ID;
import static com.herba.sdk.jetpackexample.roomRecyclerview.NoteEditActivity.UPDATED_NOTE;

public class MainRoomActivity extends AppCompatActivity implements NoteListAdapter.onDeleteClick {

    private final String TAG = this.getClass().getSimpleName();
    private Button btnAdd;
    private static int NEW_NOTE_ACTIVITY_RESULT_CODE = 1;
    public static int UPDATE_NOTE_ACTIVITY_RESULT_CODE = 2;

    private NoteViewModel noteViewModel;

    private RecyclerView recyclerview;
    private NoteListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_room);

        Log.d(TAG,TAG+" launched");

        List<Note> list = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteListAdapter(this, list,this);
        recyclerview.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(),NoteAddActivity.class),NEW_NOTE_ACTIVITY_RESULT_CODE);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_NOTE_ACTIVITY_RESULT_CODE && resultCode == RESULT_OK)
        {
            try
            {
                final String note_id = UUID.randomUUID().toString();
                Log.d(TAG,"note_id      = "+note_id);
                Log.d(TAG,"note_data    = "+data.getStringExtra(MYDATA));
                Note note = new Note(note_id,data.getStringExtra(MYDATA));
                noteViewModel.insert(note);
                Log.d(TAG,"note saved");
            }
            catch (Exception e)
            {
                Log.d(TAG,"exception = "+e.getMessage());
            }
        }
        else if(requestCode == UPDATE_NOTE_ACTIVITY_RESULT_CODE && resultCode == RESULT_OK)
        {
            Note note = new Note(data.getStringExtra(NOTE_ID),data.getStringExtra(UPDATED_NOTE));
            noteViewModel.update(note);
            Log.d(TAG,"note updated");
        }
        else
        {
            Log.d(TAG,"note not saved");
        }
    }

    @Override
    public void OnDeleteClick(Note note) {
        //delete operation
        noteViewModel.delete(note);
    }
}