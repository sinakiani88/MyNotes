package com.example.mynotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.data.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table WHERE id LIKE :id" )
    fun getNote (id : Long): NoteEntity

    @Query("SELECT * FROM note_table" )
    //fun getAllNote (): LiveData<List<NoteEntity>>
    fun getAllNote (): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (note: NoteEntity)

    @Delete
    suspend fun delete (note: NoteEntity)
}