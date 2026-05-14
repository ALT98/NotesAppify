package com.notesappify.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.notesappify.data.dao.NotesDao
import com.notesappify.data.dao.SearchDao
import com.notesappify.data.models.Notes
import com.notesappify.data.models.Search

@Database(entities = [Notes::class, Search::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getDao(): NotesDao
    abstract fun getSearchDao(): SearchDao
}