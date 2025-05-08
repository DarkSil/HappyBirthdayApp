package com.sli.happybirthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sli.happybirthdayapp.ui.theme.HappyBirthdayAppTheme
import com.sli.happybirthdayapp.view.StyledToolbar
import com.sli.happybirthdayapp.view.intro.IntroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayAppTheme {
                val controller = rememberNavController()
                val backStackEntry by controller.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination?.route
                val showToolbar = when (currentDestination) {
                    IntroScreen.route -> true
                    else -> false
                }

                // Toolbar is always outside the common paddings so it makes such annoying jumping
                // while navigating between screens where toolbar should be hidden. Personally
                // I'd use a @Composable located on required screens but from architectural pov
                // it may looks bad so I just keep the toolbar

                Scaffold(
                    topBar = {
                        StyledToolbar(showToolbar)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Base(innerPadding, controller)
                }
            }
        }
    }
}

@Composable
private fun Base(padding: PaddingValues, controller: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        AppNavigation(controller)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HappyBirthdayAppTheme {
        val controller = rememberNavController()
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Base(innerPadding, controller)
        }
    }
}