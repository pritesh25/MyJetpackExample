package com.example.myapplication.roomRecyclerview.utils

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes ORDER BY note ASC")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE id =:noteId")
    fun getNote(noteId: String): LiveData<Note>

    @Query("SELECT COUNT(*) FROM notes WHERE id =:noteId")
    fun getNoteCount(noteId: String): Int

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}