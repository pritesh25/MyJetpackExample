package com.herba.sdk.jetpackexample.roomRecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.herba.sdk.jetpackexample.R;

public class NoteEditActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATED_NOTE = "updated_note";
    private EditText edittext;
    private String noteId;
    private NoteViewModel noteViewModel;
    private LiveData<Note> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        edittext = findViewById(R.id.edittext);

        Intent intent = getIntent();
        noteId = intent.getStringExtra("note_id");

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        data = noteViewModel.getNotes(noteId);
        data.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                edittext.setText(note.getmNote());
            }
        });


    }

    //update the database
    public void update(View view) {

        String updateNoteText = edittext.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(NOTE_ID,noteId);
        intent.putExtra(UPDATED_NOTE,updateNoteText);
        setResult(RESULT_OK,intent);
        finish();
    }

    //cancel the option
    public void cancel(View view) {
        finish();
    }
}
