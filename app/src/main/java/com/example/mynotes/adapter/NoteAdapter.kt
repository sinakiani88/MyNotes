package com.example.mynotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.data.entities.NoteEntity
import com.example.mynotes.databinding.ItemlistBinding
import com.example.mynotes.utils.Extensions.dateFormat

class NoteAdapter constructor(private val onClick:(note:NoteEntity?) ->Unit):RecyclerView
.Adapter<NoteAdapter
.NoteViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        parent(parent)

    private fun parent(parent: ViewGroup):NoteViewHolder =
       NoteViewHolder(ItemlistBinding.inflate(LayoutInflater.from(parent.context),parent,false))



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class NoteViewHolder(private val itemlistBinding: ItemlistBinding):RecyclerView.ViewHolder(itemlistBinding.root){
        fun bind (noteEntity: NoteEntity?) {
            itemlistBinding.apply {
                tvTitle.text = noteEntity?.title
                tvContent.text = noteEntity?.content
                tvDate.text = noteEntity?.creationTime?.dateFormat()

                imageDelete.setOnClickListener {
                    onClick(noteEntity)
                }
            }
        }
    }
}