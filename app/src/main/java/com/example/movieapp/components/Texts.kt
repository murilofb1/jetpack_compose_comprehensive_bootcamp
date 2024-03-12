package com.example.movieapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextRecyclerTitle(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        text = text
    )
}

@Composable
fun TextRecyclerDetails(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    fontWeight: FontWeight = FontWeight.Light,
    text: String,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        style = style,
        fontWeight = fontWeight,
        text = text
    )
}

@Composable
fun TextDetailTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Bold,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    fontSize: TextUnit = 40.sp,
    text: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = style
    )
}

@Preview(showBackground = true)
@Composable
fun TextsPreview() {
    Column {
        TextRecyclerTitle(text = "TextRecyclerTitle")
        TextRecyclerDetails(text = "TextRecyclerDetails")
        TextDetailTitle(text = "TextDetailTitle")
    }
}

