package com.example.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.repository.AddNoteRepository
import java.lang.IllegalArgumentException

class AddNoteViewModelFactory constructor(private val repositoryAdd: AddNoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)){
            return AddNoteViewModel(repositoryAdd) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }

}