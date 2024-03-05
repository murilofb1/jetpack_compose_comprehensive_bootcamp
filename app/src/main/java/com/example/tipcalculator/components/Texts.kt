package com.example.tipcalculator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BodyText(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, textAlign = TextAlign.Center)
}

@Preview
@Composable
fun BodyTextPreview() {
    BodyText(text = "TESTANDO")
}


