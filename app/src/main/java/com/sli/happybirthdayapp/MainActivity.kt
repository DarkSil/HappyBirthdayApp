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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sli.happybirthdayapp.ui.theme.HappyBirthdayAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Base(innerPadding)
                }
            }
        }
    }
}

@Composable
private fun Base(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val controller = rememberNavController()
        AppNavigation(controller)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HappyBirthdayAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Base(innerPadding)
        }
    }
}