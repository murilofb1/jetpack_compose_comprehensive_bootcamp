package com.example.noteapp

import android.app.Application
import com.example.noteapp.di.AppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application() {
}