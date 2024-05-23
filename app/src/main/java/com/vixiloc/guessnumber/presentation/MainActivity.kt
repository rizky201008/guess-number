package com.vixiloc.guessnumber.presentation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import com.vixiloc.guessnumber.presentation.screens.GameScreen
import com.vixiloc.guessnumber.presentation.screens.HomeScreen
import com.vixiloc.guessnumber.presentation.screens.LevelsScreen
import com.vixiloc.guessnumber.presentation.ui.theme.GuessNumberTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            GuessNumberTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigator(screen = HomeScreen)
                }
            }
        }
    }
}