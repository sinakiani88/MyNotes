package com.example.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.entities.NoteEntity
import com.example.mynotes.repository.AddNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel constructor(private val repositoryAdd:AddNoteRepository):ViewModel() {

    fun addNote (noteEntity: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryAdd.insertNoteData(noteEntity)

        }
    }

}