package com.example.tipcalculator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalculator.R

@Composable
fun OutlinedTextDecimal(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    onValueChange: (String) -> Unit,
    icon: @Composable() () -> Unit = {},
    maxLines: Int = 1,
    isSingleLine: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        maxLines = maxLines,
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        value = text,
        onValueChange = onValueChange,
        label = { Text(text = hint) },
        leadingIcon = { icon() }
    )
}

@Preview
@Composable
fun TextPreview() {
    OutlinedTextDecimal(
        text = "",
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_money),
                contentDescription = ""
            )
        },
        onValueChange = {}
    )
}