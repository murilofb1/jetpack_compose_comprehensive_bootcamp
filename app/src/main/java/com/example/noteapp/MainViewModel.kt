package com.example.noteapp

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NoteData
import com.example.noteapp.models.NoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _noteTitle = MutableStateFlow("")
    val noteTitle = _noteTitle.asStateFlow()

    private val _noteDescription = MutableStateFlow("")
    val noteDescription = _noteDescription.asStateFlow()

    val notesList = MutableStateFlow(mutableListOf<NoteModel>())

    init {
        this.notesList.value.addAll(NoteData.loadNotes())
    }

    fun setTitle(newValue: String) {
        _noteTitle.value = newValue
    }

    fun setDescription(newValue: String) {
        _noteDescription.value = newValue
    }

    fun insertNewNote(
        noteList: SnapshotStateList<NoteModel>,
        insertNoteCallback: (NoteCallbacks) -> Unit = {}
    ) {
        when {
            _noteTitle.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_TITLE)
            _noteDescription.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_DESCRIPTION)
            else -> {
                val note = NoteModel(title = _noteTitle.value, description = _noteDescription.value)
                noteList.add(note)
                _noteTitle.value = ""
                _noteDescription.value = ""
                insertNoteCallback(NoteCallbacks.SUCCESS)
            }
        }

    }
}

enum class NoteCallbacks {
    NO_TITLE, NO_DESCRIPTION, SUCCESS
}
