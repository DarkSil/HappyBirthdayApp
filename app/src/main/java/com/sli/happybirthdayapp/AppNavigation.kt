package com.sli.happybirthdayapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sli.happybirthdayapp.presentation.CongratsViewModel
import com.sli.happybirthdayapp.presentation.IntroViewModel
import com.sli.happybirthdayapp.view.congrats.CongratsScreen
import com.sli.happybirthdayapp.view.intro.IntroScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AppNavigation(controller: NavHostController) {
    NavHost(controller, startDestination = IntroScreen.route) {
        composable(IntroScreen.route) {
            val viewModel: IntroViewModel = viewModel()
            IntroScreen(viewModel) {
                // TODO Save blocking
                controller.navigate(CongratsScreen.route)
            }
        }

        composable(CongratsScreen.route) {
            val viewModel: CongratsViewModel = viewModel()
            val scope = rememberCoroutineScope()
            var visible by rememberSaveable { mutableStateOf(true) }

            AndroidView(
                factory = {
                    val view = ComposeView(it)
                    view.setContent {
                        CongratsScreen(viewModel, visible) {
                            visible = false
                            scope.launch {
                                delay(200)
                                withContext(Dispatchers.Main) {
                                    viewModel.shareScreen(view)
                                }
                                visible = true
                            }
                        }
                    }
                    view
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}