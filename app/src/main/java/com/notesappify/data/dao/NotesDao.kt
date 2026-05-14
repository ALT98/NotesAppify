package com.notesappify.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.notesappify.data.models.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Query("select * from notes")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("select * from notes where id = :id")
    suspend fun getCurrentNote(id: Int): Notes
}