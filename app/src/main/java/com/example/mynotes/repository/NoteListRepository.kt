package com.example.mynotes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mynotes.data.Appdatabase
import com.example.mynotes.data.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class NoteListRepository(private val appDatabase: Appdatabase) {


     /*fun getAllNoteData ():LiveData<List<NoteEntity>>{
        return Transformations.map(appDatabase.noteDao.getAllNote()){
            it
        }
    }*/

    fun getAllNote() = flow {
        emit(appDatabase.noteDao.getAllNote())
    }.flowOn(Dispatchers.IO)

    suspend fun delete (noteEntity: NoteEntity){
        appDatabase.noteDao.delete(noteEntity)
    }
}