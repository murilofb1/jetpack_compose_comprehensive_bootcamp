package com.example.movieapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonSimple(onClick: () -> Unit, label: String) {
    Button(
        onClick = onClick
    ) {
        Text(text = label.uppercase())
    }
}

@Composable
fun IconButtonSimple(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String = "",
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(contentColor = color)
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtons() {
    Column {
        ButtonSimple(onClick = { /*TODO*/ }, label = "EXAMPLE")
        IconButtonSimple(onClick = { /*TODO*/ }, imageVector = Icons.Filled.ArrowBack)
    }
}
