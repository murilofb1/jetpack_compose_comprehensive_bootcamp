package com.example.noteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.components.MyButton
import com.example.noteapp.components.MyTextField

private lateinit var viewModel: MainViewModel

@Composable
fun MainScreen(vm: MainViewModel) {
    viewModel = vm
    MainScreenBackground {
        NewNoteForm()
        Divider()
        NotesList()
    }
}

@Composable
private fun NotesList() {
    LazyColumn {

    }
}

@Composable
private fun NewNoteForm() {
    MyTextField(
        textState = viewModel.noteTitle.collectAsState(),
        label = "Title", onValueChange = { viewModel.setTitle(it) }
    )
    MyTextField(
        textState = viewModel.noteDescription.collectAsState(),
        label = "Note", onValueChange = { viewModel.setDescription(it) }
    )
    MyButton(
        onClick = {
            viewModel.insertNewNote {
                when (it) {
                    NoteCallbacks.NO_TITLE -> TODO()
                    NoteCallbacks.NO_DESCRIPTION -> TODO()
                    NoteCallbacks.SUCCESS -> TODO()
                }
            }
        }, label = "salvar"
    )
}


@Composable
fun MainScreenBackground(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { content() }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(MainViewModel())
}


