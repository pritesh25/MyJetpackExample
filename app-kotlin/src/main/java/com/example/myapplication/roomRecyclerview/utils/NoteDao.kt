package com.example.myapplication.roomRecyclerview.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE id =:noteId")
    fun getNotes(noteId: String): LiveData<Note>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)


}