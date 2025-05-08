package com.sli.happybirthdayapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sli.happybirthdayapp.presentation.IntroViewModel
import com.sli.happybirthdayapp.view.congrats.CongratsScreen
import com.sli.happybirthdayapp.view.intro.IntroScreen

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
            CongratsScreen()
        }
    }
}