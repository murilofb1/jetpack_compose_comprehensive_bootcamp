package com.example.tipcalculator.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.R

@Preview
@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.ic_add),
    contentDescription: String = "",
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .size(40.dp)
        ,shape = CircleShape,
        onClick = onClick
    ) {
        Icon(painter = painter, contentDescription = contentDescription)
    }

}