package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.noteapp.data.NoteData
import com.example.noteapp.models.NoteModel
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notes = remember {
                mutableStateListOf<NoteModel>().apply { addAll(NoteData.loadNotes()) }
            }

            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(viewModel, notes)
            }
        }
    }
}