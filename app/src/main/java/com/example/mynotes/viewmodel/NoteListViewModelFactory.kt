package com.example.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.data.Appdatabase
import com.example.mynotes.repository.NoteListRepository
import java.lang.IllegalArgumentException

class NoteListViewModelFactory constructor(private val repository: NoteListRepository): ViewModelProvider
.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)){
            return NoteListViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }

}