package com.example.mynotes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynotes.data.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table WHERE id LIKE :id" )
    fun getNote (id : Long): NoteEntity

    @Query("SELECT * FROM note_table" )
    fun getAllNote (): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (note: NoteEntity)
}