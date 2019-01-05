package com.herba.sdk.jetpackexample.roomRecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.roomRecyclerview.utils.Note;
import com.herba.sdk.jetpackexample.roomRecyclerview.utils.NoteViewModel;

public class NoteEditActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATED_NOTE = "updated_note";
    private final String TAG = this.getClass().getSimpleName();
    private EditText edittext;
    private String noteId;
    private NoteViewModel noteViewModel;
    private LiveData<Note> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        edittext = findViewById(R.id.edittext);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            noteId = bundle.getString("db_note_id");
            Log.d(TAG, "bundle is not null, noteId = " + noteId);

            noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

            data = noteViewModel.getNotes(noteId);
            data.observe(this, new Observer<Note>() {
                @Override
                public void onChanged(Note note) {
                    edittext.setText(note.getmNote());
                }
            });
        } else {
            Log.d(TAG, "bundle is null");
        }
    }

    //update the database
    public void update(View view) {

        String updateNoteText = edittext.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(NOTE_ID, noteId);
        intent.putExtra(UPDATED_NOTE, updateNoteText);
        setResult(RESULT_OK, intent);
        finish();
    }

    //cancel the option
    public void cancel(View view) {
        finish();
    }
}
