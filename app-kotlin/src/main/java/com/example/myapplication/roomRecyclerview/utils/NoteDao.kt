package com.example.myapplication.roomRecyclerview.utils

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes ORDER BY appLabel ASC")
    val allApps: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes WHERE appPackage =:appPackage")
    fun getApp(appPackage: String): LiveData<Note>

    @Query("SELECT COUNT(*) FROM notes WHERE appPackage =:appPackage")
    fun getAppCount(appPackage: String): Int

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}