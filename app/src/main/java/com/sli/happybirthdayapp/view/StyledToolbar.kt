package com.sli.happybirthdayapp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.ui.theme.Black
import com.sli.happybirthdayapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledToolbar(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(tween(300)) + fadeIn(tween(300)),
        exit = shrinkVertically(tween(300)) + fadeOut(tween(300))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = White,
                titleContentColor = Black
            )
        )
    }
}