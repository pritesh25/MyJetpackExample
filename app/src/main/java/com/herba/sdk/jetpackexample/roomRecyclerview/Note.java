package com.herba.sdk.jetpackexample.roomRecyclerview;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {


    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name = "note")
    public String mNote;

    public Note(String id, String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getmNote() {
        return this.mNote;
    }

}
