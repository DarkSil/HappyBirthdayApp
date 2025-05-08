package com.sli.happybirthdayapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sli.happybirthdayapp.ui.theme.ButtonColor

@Composable
fun CircularCard(
    containerColor: Color,
    modifier: Modifier? = null,
    contentColor : Color? = null,
    border: BorderStroke? = null,
    content: @Composable (ColumnScope) -> Unit
) {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp,0.dp,0.dp,0.dp,0.dp,0.dp),
        colors = CardDefaults.cardColors(
            containerColor,
            contentColor ?: Color.Transparent,
            Color.Transparent,
            Color.Transparent
        ),
        content = content,
        border = border,
        modifier = modifier ?: Modifier
    )
}

@Preview
@Composable
private fun Preview() {
    CircularCard(
        modifier = Modifier
            .size(100.dp),
        containerColor = ButtonColor
    ) {}
}