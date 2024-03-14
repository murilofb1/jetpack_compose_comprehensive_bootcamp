package com.example.noteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.components.MyAppBar
import com.example.noteapp.components.MyButton
import com.example.noteapp.components.MyTextField
import com.example.noteapp.components.NoteRecycler
import com.example.noteapp.data.NoteData
import com.example.noteapp.models.NoteModel

private lateinit var viewModel: MainViewModel
private lateinit var notesList: SnapshotStateList<NoteModel>

@Composable
fun MainScreen(vm: MainViewModel, list: SnapshotStateList<NoteModel>) {
    viewModel = vm
    notesList = list

    MainScreenBackground {
        NewNoteForm()
        Divider()
        NotesList(
            modifier = Modifier.padding(top = 10.dp),
            notesList = notesList
        )
    }
}

@Composable
private fun NewNoteForm() {
    MyTextField(
        modifier = Modifier.padding(bottom = 10.dp),
        textState = viewModel.noteTitle.collectAsState(),
        label = "Title", onValueChange = { viewModel.setTitle(it) }
    )
    MyTextField(
        modifier = Modifier.padding(bottom = 5.dp),
        textState = viewModel.noteDescription.collectAsState(),
        label = "Note", onValueChange = { viewModel.setDescription(it) }
    )
    MyButton(
        onClick = {
            viewModel.insertNewNote(notesList) {
                when (it) {
                    NoteCallbacks.NO_TITLE -> {}
                    NoteCallbacks.NO_DESCRIPTION -> {}
                    NoteCallbacks.SUCCESS -> {}
                }
            }
        }, label = "salvar"
    )
}

@Composable
private fun NotesList(modifier: Modifier = Modifier, notesList: SnapshotStateList<NoteModel>) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        NoteRecycler(notesList)
    }

}


@Composable
fun MainScreenBackground(content: @Composable () -> Unit) {
    Scaffold(
        topBar = { MyAppBar(LocalContext.current.getString(R.string.app_name)) }
    ) {
        Surface(Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { content() }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(MainViewModel(), remember {
        val notes = mutableStateListOf<NoteModel>()
        notes.addAll(NoteData.loadNotes())
        notes
    })
}


