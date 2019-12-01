package com.example.myapplication.roomRecyclerview.utils

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = this.javaClass.simpleName

    private val noteDB = NoteRoomDatabase.getDatabase(application)
    private val noteDao = noteDB!!.noteDao()
    var allNotes = noteDao.allNotes

    fun insert(note: Note) {
        //InsertAsyncTask(noteDao).execute(note)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "addData called")
            try {
                noteDao.insert(note)
            } catch (e: Exception) {
                Log.d(TAG, "catch error = ${e.message}")
            }
        }
    }

    fun getNotes(noteId: String): LiveData<Note> {
        return noteDao.getNotes(noteId)
    }

    fun update(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    private inner class InsertAsyncTask(internal var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }

    private inner class UpdateAsyncTask(internal var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.update(notes[0])
            return null
        }
    }

    private inner class DeleteAsyncTask(internal var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.delete(notes[0])
            return null
        }
    }
}