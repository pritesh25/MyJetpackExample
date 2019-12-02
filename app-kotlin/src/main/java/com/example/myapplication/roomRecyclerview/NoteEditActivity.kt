package com.example.myapplication.roomRecyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.roomRecyclerview.utils.Note
import com.example.myapplication.roomRecyclerview.utils.NoteViewModel

class NoteEditActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private var edittext: EditText? = null
    private lateinit var noteId: String
    private var noteViewModel: NoteViewModel? = null
    private var data: LiveData<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        edittext = findViewById(R.id.edittext)

        val bundle = intent.extras

        if (bundle != null) {
            noteId = bundle.getString("db_note_id")!!
            Log.d(TAG, "bundle is not null, noteId = " + noteId!!)

            noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

            data = noteViewModel!!.getNotes(noteId)
            data!!.observe(this, Observer<Note> { note -> edittext!!.setText(note.getNote()) })
        } else {
            Log.d(TAG, "bundle is null")
        }
    }

    //update the database
    fun update(view: View) {
        val updateNoteText = edittext!!.text.toString()
        val intent = Intent()
        intent.putExtra(NOTE_ID, noteId)
        intent.putExtra(UPDATED_NOTE, updateNoteText)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    //cancel the option
    fun cancel(view: View) {
        finish()
    }

    companion object {

        val NOTE_ID = "note_id"
        val UPDATED_NOTE = "updated_note"
    }
}
