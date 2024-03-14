package com.example.noteapp.models

import java.time.LocalDateTime
import java.util.UUID

data class NoteModel(
    val title: String,
    val description: String,
    val id: UUID = UUID.randomUUID(),
    val dateTime: LocalDateTime = LocalDateTime.now()
)
