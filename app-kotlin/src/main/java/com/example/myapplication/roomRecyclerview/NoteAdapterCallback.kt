package com.example.myapplication.roomRecyclerview

import com.example.myapplication.roomRecyclerview.utils.Note

interface NoteAdapterCallback {
        fun OnDeleteItemClick(note: Note)
    }