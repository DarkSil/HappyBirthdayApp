package com.sli.happybirthdayapp.view.intro

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.ui.theme.ButtonColor
import com.sli.happybirthdayapp.ui.theme.White
import com.sli.happybirthdayapp.ui.theme.White70

@Composable
fun StyledTextField(
    label: String,
    value: String,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedLabelColor = White,
            focusedLabelColor = White70,
            unfocusedContainerColor = ButtonColor,
            focusedContainerColor = ButtonColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White,
            disabledContainerColor = ButtonColor,
            disabledTextColor = White,
            disabledLabelColor = if (value.isBlank()) White else White70,
            disabledIndicatorColor = Color.Transparent
        ),
        readOnly = readOnly,
        enabled = !readOnly,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp, 0.dp)
    )
}

@Preview
@Composable
private fun Preview() {
    StyledTextField(stringResource(R.string.name), "George") { }
}