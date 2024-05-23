package com.vixiloc.guessnumber.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vixiloc.guessnumber.R
import com.vixiloc.guessnumber.domain.model.GameLevel
import com.vixiloc.guessnumber.domain.model.Level
import com.vixiloc.guessnumber.presentation.event.LevelsScreenEvent
import com.vixiloc.guessnumber.presentation.view_model.LevelsScreenViewModel
import org.koin.androidx.compose.koinViewModel

object LevelsScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: LevelsScreenViewModel = koinViewModel()
        val navigator = LocalNavigator.currentOrThrow
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val state = viewModel.state.value

        val selectedLevel = state.selectedLevel
        val levels = state.levels
        val onEvent = viewModel::onEvent

        Scaffold(
            modifier = Modifier,
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        modifier = Modifier.padding(bottom = 30.dp),
                        text = "Current Level: ${state.setting?.gameLevel?.name ?: selectedLevel.level}",
                        style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                    )
                }, navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .clickable {
                                navigator.pop()
                            },
                        contentScale = ContentScale.Fit
                    )
                })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                levels.forEach { level ->
                    Box(
                        modifier = Modifier
                            .width(screenWidth / 1.5f)
                            .padding(vertical = 10.dp)
                            .clickable {
                                onEvent(LevelsScreenEvent.SelectLevel(level))
                                onEvent(LevelsScreenEvent.UpdateLevel)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(300.dp)
                                .zIndex(0f),
                            painter = if (level == selectedLevel) {
                                painterResource(id = R.drawable.level_button_1_selected)
                            } else {
                                painterResource(id = R.drawable.level_button_1)
                            },
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Column(
                            modifier = Modifier
                                .zIndex(1f)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = level.name,
                                modifier = Modifier
                                    .padding(top = 30.dp)
                                    .zIndex(1f),
                                style = MaterialTheme.typography.displayMedium.copy(color = Color.White)
                            )
                            Text(
                                text = level.description,
                                modifier = Modifier
                                    .padding(top = 100.dp)
                                    .zIndex(1f),
                                style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
                            )
                        }
                    }
                }
            }
        }
    }
}