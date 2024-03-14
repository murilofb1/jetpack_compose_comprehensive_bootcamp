package com.example.noteapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String? = null
) {
    Button(modifier = modifier, onClick = onClick) {
        label?.let { Text(text = label.uppercase()) }
    }
}