package com.example.mynotes.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
 @Entity(tableName = "note_table")
  data class NoteEntity(
        @PrimaryKey(autoGenerate = true)
        val id :Long = 0L,
        @ColumnInfo(name = "title")
        var title : String ? = null,
        @ColumnInfo(name = "content")
        var content : String ? = null,
        @ColumnInfo(name = "time")
        var creationTime : Long ? = 0L,
  ):Parcelable
