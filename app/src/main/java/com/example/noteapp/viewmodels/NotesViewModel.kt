package com.example.noteapp.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.NoteApplication
import com.example.noteapp.data.NoteData
import com.example.noteapp.models.NoteEntity
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _noteTitle = MutableStateFlow("")
    val noteTitle = _noteTitle.asStateFlow()

    private val _noteDescription = MutableStateFlow("")
    val noteDescription = _noteDescription.asStateFlow()


    private val _notesList =     MutableStateFlow<List<NoteEntity>>(listOf())
    val notesList = _notesList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().collect {
                _notesList.value = it

            }
        }
    }

    fun setTitle(newValue: String) {
        _noteTitle.value = newValue
    }

    fun setDescription(newValue: String) {
        _noteDescription.value = newValue
    }

    fun removeNote(note: NoteEntity) {
//        _notesList.remove(note)
        viewModelScope.launch { repository.deleteNote(note) }
    }

    fun insertNewNote(
        insertNoteCallback: (NoteCallbacks) -> Unit = {}
    ) {
        when {
            _noteTitle.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_TITLE)
            _noteDescription.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_DESCRIPTION)
            else -> {
                val note =
                    NoteEntity(title = _noteTitle.value, description = _noteDescription.value)
                viewModelScope.launch { repository.insertNote(note) }
                _noteTitle.value = ""
                _noteDescription.value = ""
            }
        }

        /*
                when {
                    _noteTitle.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_TITLE)
                    _noteDescription.value.isEmpty() -> insertNoteCallback(NoteCallbacks.NO_DESCRIPTION)
                    else -> {
                        val note =
                            NoteEntity(title = _noteTitle.value, description = _noteDescription.value)
                        _notesList.add(note)
                        _noteTitle.value = ""
                        _noteDescription.value = ""
                        insertNoteCallback(NoteCallbacks.SUCCESS)
                    }
                }*/

    }
}

enum class NoteCallbacks {
    NO_TITLE, NO_DESCRIPTION, SUCCESS
}
