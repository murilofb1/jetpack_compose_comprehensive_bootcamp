package com.example.noteapp.helpers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatAsDate(): String {
    return this.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
}