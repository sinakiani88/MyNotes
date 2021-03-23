package com.example.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.data.dao.NoteDao
import com.example.mynotes.data.entities.NoteEntity

@Database(entities = [NoteEntity ::class], version = 1,exportSchema = false )

abstract class Appdatabase : RoomDatabase() {

    abstract val noteDao : NoteDao

    companion object{
        @Volatile
        var ISTANCE : Appdatabase? = null
        fun getInstance(context: Context) : Appdatabase?{
            synchronized(this){
                ISTANCE?.let {
                    ISTANCE
                } ?: run {
                    ISTANCE = Room.databaseBuilder(context,Appdatabase::class.java,"note_table")
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
                return ISTANCE
            }
        }
    }
}