package com.example.myapplication.roomRecyclerview.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(@field:PrimaryKey
           var id: String, @field:ColumnInfo(name = "note")
           var mNote: String) {

    fun getmNote(): String {
        return this.mNote
    }

}
