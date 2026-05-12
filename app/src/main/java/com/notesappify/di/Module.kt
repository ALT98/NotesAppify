package com.notesappify.di

import android.content.Context
import androidx.room.Room
import com.notesappify.data.NotesDao
import com.notesappify.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun providesNotesDatabase(
        @ApplicationContext context: Context
    ) : NotesDatabase {
        return Room.databaseBuilder(
            context =  context,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesGetDao(notesDatabase: NotesDatabase) : NotesDao{
        return notesDatabase.getDao()
    }
}