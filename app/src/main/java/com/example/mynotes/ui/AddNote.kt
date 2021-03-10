package com.example.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynotes.R
import com.example.mynotes.data.Appdatabase
import com.example.mynotes.data.entities.NoteEntity
import com.example.mynotes.databinding.FragmentAddNoteBinding
import com.example.mynotes.repository.AddNoteRepository
import com.example.mynotes.utils.Extensions.checkStringIsNotNullOrEmpty
import com.example.mynotes.viewmodel.AddNoteViewModel
import com.example.mynotes.viewmodel.AddNoteViewModelFactory


class AddNote : Fragment() {

    private var _binding:FragmentAddNoteBinding?= null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory:AddNoteViewModelFactory
    //private lateinit var viewModel:AddNoteViewModel
    private val viewModel by viewModels<AddNoteViewModel> {viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater,container,false)

        viewModelFactory = AddNoteViewModelFactory(AddNoteRepository(Appdatabase.getInstance(requireContext())!!))
        //viewModel = ViewModelProvider(this,viewModelFactory).get(AddNoteViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnCheck.setOnClickListener {
                if (!etTitle.text.toString().checkStringIsNotNullOrEmpty() or !etContent.text.toString().checkStringIsNotNullOrEmpty()){
                    saveNotes()
                    findNavController().popBackStack()
                }else{
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun FragmentAddNoteBinding.saveNotes() {
        NoteEntity(title = "",content = "",creationTime = 0L).also {
            it.title = etTitle.text.toString()
            it.content = etTitle.text.toString()
            System.currentTimeMillis().apply {
                it.creationTime = this
            }
        }.run {
            viewModel.addNote(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}