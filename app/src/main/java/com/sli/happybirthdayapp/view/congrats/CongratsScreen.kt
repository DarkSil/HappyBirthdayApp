package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.presentation.SharedViewModel
import com.sli.happybirthdayapp.utils.RandomBackground

object CongratsScreen {
    const val route = "congrats"
}

@Composable
fun CongratsScreen(viewModel: SharedViewModel) {
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
        ImagePlaceholder(background, viewModel)
    }
}

@Preview
@Composable
private fun Preview() {
    CongratsScreen(viewModel())
}