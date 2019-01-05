package com.herba.sdk.jetpackexample.roomRecyclerview.utils;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();

    private NoteDao noteDao;
    private NoteRoomDatabase noteDB;
    private LiveData<List<Note>> allNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteDB = NoteRoomDatabase.getDatabase(application);
        noteDao = noteDB.noteDao();
        allNote = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNote;
    }

    public LiveData<Note> getNotes(String noteId) {
        return noteDao.getNotes(noteId);
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        public UpdateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

}