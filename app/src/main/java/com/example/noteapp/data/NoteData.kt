package com.example.noteapp.data

import com.example.noteapp.models.NoteEntity

class NoteData {
    companion object {
        fun loadNotes(): List<NoteEntity> {
            return listOf(
                NoteEntity(title = "A good day", description = "We went on a vacation by the lake"),
                NoteEntity(
                    title = "Android Compose",
                    description = "Working on Android Compose course today"
                ),
                NoteEntity(title = "Keep at it...", description = "Sometimes things just happen"),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(
                    title = "A movie day",
                    description = "Watching a movie with family today"
                ),
                NoteEntity(title = "A movie day", description = "Watching a movie with family")

            )
        }
    }
}