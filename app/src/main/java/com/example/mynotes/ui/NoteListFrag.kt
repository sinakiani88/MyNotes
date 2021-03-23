package com.example.mynotes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mynotes.R
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.data.Appdatabase
import com.example.mynotes.data.entities.NoteEntity
import com.example.mynotes.databinding.FragmentNoteListBinding
import com.example.mynotes.repository.NoteListRepository
import com.example.mynotes.viewmodel.NoteListViewModel
import com.example.mynotes.viewmodel.NoteListViewModelFactory
import kotlinx.coroutines.flow.collect


class NoteListFrag : Fragment() {

    private var _binding: FragmentNoteListBinding?= null
    private val binding get() = _binding!!

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModelFactory: NoteListViewModelFactory
    private val viewModel by viewModels<NoteListViewModel> {viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater,container,false)
        viewModelFactory = NoteListViewModelFactory(NoteListRepository(Appdatabase.getInstance
        (requireContext())!!))
        //noteAdapter=NoteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAddNote.setOnClickListener {
                findNavController().navigate(R.id.action_noteList_to_addNote)
            }

            //rvNoteList.adapter = noteAdapter
            /*viewModel.allNote.observe(viewLifecycleOwner){

                rvNoteList.adapter = NoteAdapter { note ->
                    note?.let {
                        viewModel.deleteNote(it)
                    }
                }.apply {
                    differ.submitList(it)
                }
            }*/
            lifecycleScope.launchWhenResumed {
                viewModel.getAllNote().collect{
                    rvNoteList.adapter = NoteAdapter{ note ->
                        note?.let {
                            deleteNoteDialoge(it)
                        }
                    }.apply {
                        differ.submitList(it)
                    }
                }
            }
        }

    }
    private fun deleteNoteDialoge(noteEntity: NoteEntity){
        AlertDialog.Builder(requireContext()).setTitle("Delete Note").setMessage("are you sure?")
                .setPositiveButton("yes"){_,_->
                    viewModel.deleteNote(noteEntity)
                }.setNegativeButton("no"){_,_->}.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}