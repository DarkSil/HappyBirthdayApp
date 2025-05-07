package com.sli.happybirthdayapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sli.happybirthdayapp.view.congrats.CongratsScreen
import com.sli.happybirthdayapp.view.intro.IntroScreen

@Composable
fun AppNavigation(controller: NavHostController) {
    NavHost(controller, startDestination = IntroScreen.route) {
        composable(IntroScreen.route) {
            IntroScreen {
                controller.navigate(CongratsScreen.route)
            }
        }

        composable(CongratsScreen.route) {
            CongratsScreen()
        }
    }
}