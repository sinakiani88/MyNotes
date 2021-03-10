package com.example.mynotes.repository

import androidx.room.Database
import com.example.mynotes.data.Appdatabase
import com.example.mynotes.data.entities.NoteEntity

class AddNoteRepository constructor(private val appDatabase: Appdatabase) {
    suspend fun insertNoteData (noteEntity: NoteEntity){
        appDatabase.noteDao.insert(noteEntity)
    }
}