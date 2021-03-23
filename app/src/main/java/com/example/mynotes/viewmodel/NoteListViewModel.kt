package com.example.mynotes.viewmodel

import androidx.lifecycle.*
import com.example.mynotes.data.entities.NoteEntity
import com.example.mynotes.repository.AddNoteRepository
import com.example.mynotes.repository.NoteListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NoteListViewModel constructor(private val repository:NoteListRepository):ViewModel() {

    private var _allNote = MutableLiveData<List<NoteEntity>>()

    /*val allNote:LiveData<List<NoteEntity>>
    get() = repository.getAllNoteData()*/



    init {
        getAllNote()
    }

    fun getAllNote() = flow {

            repository.getAllNote().collect {
                it.collect { list ->
                    emit(list)
                }
            }
    }.flowOn(Dispatchers.IO)

    fun deleteNote (noteEntity: NoteEntity){
        viewModelScope.launch {
            repository.delete(noteEntity)
        }
    }
}