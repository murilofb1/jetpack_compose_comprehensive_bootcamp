package com.example.noteapp.repository

import com.example.noteapp.data.NoteDao
import com.example.noteapp.models.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun updateNote(note: NoteEntity) = noteDao.update(note)
    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
    suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)
    suspend fun getNoteById(noteId: String) = noteDao.getNoteById(noteId)
    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

    fun getAllNotes(): Flow<List<NoteEntity>> =
        noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()

}