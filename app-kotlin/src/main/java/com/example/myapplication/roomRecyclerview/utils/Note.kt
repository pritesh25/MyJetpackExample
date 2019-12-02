package com.example.myapplication.roomRecyclerview.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(
        @field:PrimaryKey
        var id: String,
        @field:ColumnInfo(name = "note")
        var textNote: String) {

    fun getNote(): String {
        return this.textNote
    }
}