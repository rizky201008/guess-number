package com.vixiloc.guessnumber.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vixiloc.guessnumber.R

object HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(title = { }, actions = {
                    Image(
                        painter = painterResource(id = R.drawable.level_button),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .clickable {
                                navigator.push(LevelsScreen)
                            },
                        contentScale = ContentScale.Fit
                    )
                })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 30.dp),
                    text = "Guess Number",
                    style = MaterialTheme.typography.displaySmall
                )
                Image(
                    painter = painterResource(id = R.drawable.play_button),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.large)
                        .clickable {
                            navigator.push(GameScreen)
                        })
            }
        }
    }

}