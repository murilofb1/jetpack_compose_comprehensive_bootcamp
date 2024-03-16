package com.example.noteapp.data

import androidx.room.TypeConverter
import java.time.Instant
import java.util.Date

class Converters {
    @TypeConverter
    fun dateToLong(date: Date): Long = date.time

    @TypeConverter
    fun longToDate(long: Long): Date = Date(long)
}