package com.vixiloc.guessnumber.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.rememberDynamicColorScheme

@Composable
fun GuessNumberTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = rememberDynamicColorScheme(Color(0xffFEB941), true)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}