package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sli.happybirthdayapp.utils.RandomBackground

object CongratsScreen {
    const val route = "congrats"
}

@Preview
@Composable
fun CongratsScreen() {
    val background by rememberSaveable(
        saver = Saver(
            save = { randomBackground -> randomBackground.value },
            restore = { mutableStateOf(it) }
        )
    ) {
        mutableStateOf(RandomBackground.getRandomBackground())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background.color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = background.id.toString())
    }
}