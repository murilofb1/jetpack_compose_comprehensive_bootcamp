package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_tbl")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_tbl WHERE id = :noteId")
    suspend fun getNoteById(noteId: String): NoteEntity

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM notes_tbl")
    suspend fun deleteAllNotes()
}

