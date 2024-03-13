package com.example.noteapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    textState: State<String>,
    onValueChange: (String) -> Unit = {},
    label: String? = null
) {
    TextField(
        modifier = modifier,
        value = textState.value,
        onValueChange = onValueChange,
        label = { label?.let { Text(text = it) } }
    )
}

@Preview
@Composable
fun PreviewTextField() {
    Column {
        MyTextField(
            textState = remember { mutableStateOf("Teste") },
            label = "Insira aqui o seu texto"
        )
    }
}