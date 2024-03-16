package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.noteapp.screens.MainScreen
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<NotesViewModel>()
            NoteAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                MainScreen(viewModel)
            }
        }
    }
}