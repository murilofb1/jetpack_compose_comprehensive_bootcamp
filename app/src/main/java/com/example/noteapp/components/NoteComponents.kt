package com.example.noteapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.NoteData
import com.example.noteapp.helpers.formatAsDate
import com.example.noteapp.models.NoteModel

@Composable
fun NoteRow(
    note: NoteModel,
    onClick: (NoteModel) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp))
            .clickable { onClick(note) },
        color = Color.LightGray,
        shadowElevation = 10.dp
    ) {
        Column(
            Modifier.padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 5.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Criado em: ${note.dateTime.formatAsDate()}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun NoteRecycler(notesList: SnapshotStateList<NoteModel>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(notesList) {
            NoteRow(note = it)
        }
    }
}

@Preview
@Composable
fun PreviewNoteRow() {
    NoteRow(NoteData.loadNotes()[0])
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteRecycler() {
//    NoteRecycler(notesList = NoteData.loadNotes())
}