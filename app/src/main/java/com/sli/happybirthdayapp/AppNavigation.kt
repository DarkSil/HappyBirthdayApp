package com.sli.happybirthdayapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sli.happybirthdayapp.presentation.SharedViewModel
import com.sli.happybirthdayapp.view.congrats.CongratsScreen
import com.sli.happybirthdayapp.view.intro.IntroScreen

// The ViewModel here is same for both screens but those screens are working with different
// instances. I made it that way so those two screens are separated and not related to each other.
// In fact this approach creates a small gap while data loading for second screen and I know
// it may be fixed by just passing the data from one screen to another. I also did not implemented
// Circular Progress Bar here which I'd implement in real case

@Composable
fun AppNavigation(controller: NavHostController) {
    NavHost(controller, startDestination = IntroScreen.route) {
        composable(IntroScreen.route) {
            val viewModel: SharedViewModel = viewModel()
            IntroScreen(viewModel) {
                // TODO Save blocking
                controller.navigate(CongratsScreen.route)
            }
        }

        composable(CongratsScreen.route) {
            val viewModel: SharedViewModel = viewModel()
            CongratsScreen(viewModel)
        }
    }
}