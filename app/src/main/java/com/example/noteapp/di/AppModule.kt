package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NotesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesRoomDatabase): NoteDao = notesDatabase.getDao()

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): NotesRoomDatabase =
        Room.databaseBuilder(context, NotesRoomDatabase::class.java, "notes_database")
            .fallbackToDestructiveMigration()
            .build()

}