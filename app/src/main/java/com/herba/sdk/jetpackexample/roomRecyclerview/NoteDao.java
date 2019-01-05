package com.herba.sdk.jetpackexample.roomRecyclerview;

import android.service.voice.VoiceInteractionService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id =:noteId")
    LiveData<Note> getNotes(String noteId);

    @Update
    void update(Note note);



}