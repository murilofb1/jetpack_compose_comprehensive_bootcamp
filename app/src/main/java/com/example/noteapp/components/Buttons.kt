package com.example.noteapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyButton(onClick: () -> Unit, label: String? = null) {
    Button(onClick = onClick) {
        label?.let { Text(text = label.uppercase()) }
    }
}