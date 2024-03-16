package com.example.noteapp.helpers

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

fun LocalDateTime.formatAsDate(): String {
    return this.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
}


fun LocalDate.formatAsDate(): String {
    return this.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
}

fun Date.formatAsDate(): String {
    return this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().formatAsDate()
}
