package com.vixiloc.guessnumber.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vixiloc.guessnumber.R
import com.vixiloc.guessnumber.domain.model.GameLevel
import com.vixiloc.guessnumber.presentation.components.rememberImeState
import com.vixiloc.guessnumber.presentation.event.GameScreenEvent
import com.vixiloc.guessnumber.presentation.view_model.GameScreenViewModel
import org.koin.androidx.compose.koinViewModel

object GameScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: GameScreenViewModel = koinViewModel()
        val state = viewModel.state.value
        val onEvent = viewModel::onEvent
        val navigator = LocalNavigator.currentOrThrow
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        val focusManager = LocalFocusManager.current
        val imeState = rememberImeState()
        val scrollState = rememberScrollState()

        LaunchedEffect(Unit) {
            onEvent(GameScreenEvent.UpdateSettings)
        }

        Scaffold(
            modifier = Modifier,
            topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        modifier = Modifier.padding(bottom = 30.dp),
                        text = "Level: ${state.setting?.gameLevel ?: GameLevel.EASY}",
                        style = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center)
                    )
                }, navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.home_button),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .clickable {
                                navigator.pop()
                                navigator.push(HomeScreen)
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
                    .verticalScroll(state = scrollState),
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.width(screenWidth / 3f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rectangle_background),
                            contentDescription = null
                        )
                        Text(
                            text = "Attempt : ${state.attempts}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(screenWidth / 3f)
                            .clickable {
                                onEvent(
                                    GameScreenEvent.ShowConfirmDialog(
                                        "Confirmation",
                                        "Are you sure you want to restart the game?"
                                    )
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rectangle_background),
                            contentDescription = null
                        )
                        Text(text = "Restart", style = MaterialTheme.typography.headlineSmall)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(screenHeight / 3f)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.large
                        )
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.large
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (state.answerCorrect) "Correct!" else "???",
                            style = MaterialTheme.typography.displayMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                    text = "Guess the number",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                BasicTextField(
                    value = state.userAnswer,
                    onValueChange = { onEvent(GameScreenEvent.ChangeAnswer(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(10.dp),
                    textStyle = MaterialTheme.typography.displaySmall.copy(
                        color = Color.White,
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    })
                )

                Button(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .fillMaxWidth(),
                    onClick = {
                        onEvent(GameScreenEvent.SubmitAnswer)
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Submit Answer",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                if (state.showMessageDialog) {
                    state.messageDialogText?.let { message ->
                        AlertDialog(
                            onDismissRequest = {
                                onEvent(GameScreenEvent.DismissConfirmDialog)
                            },
                            confirmButton = {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        onEvent(GameScreenEvent.DismissMessageDialog)
                                    }) {

                                    Text(if (state.answerCorrect) "Play Again" else "Try Again")
                                }
                            },
                            title = {
                                Text(
                                    text = state.messageDialogTitle ?: "Info",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            text = {
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            })
                    }
                }

                if (state.showConfirmDialog) {
                    state.confirmDialogText?.let { message ->
                        AlertDialog(
                            onDismissRequest = {
                                onEvent(GameScreenEvent.DismissConfirmDialog)
                            },
                            confirmButton = {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        onEvent(GameScreenEvent.ResetGame)
                                    }) {
                                    Text("Yes")
                                }
                            },
                            dismissButton = {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        onEvent(GameScreenEvent.DismissConfirmDialog)
                                    }) {
                                    Text("Cancel")
                                }
                            },
                            title = {
                                Text(
                                    text = state.confirmDialogTitle ?: "Confirmation",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            text = {
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            })
                    }
                }

                if (state.showConfirmDialog1) {
                    state.confirmDialogText?.let { message ->
                        AlertDialog(
                            onDismissRequest = {
                                onEvent(GameScreenEvent.DismissConfirmDialog1)
                            },
                            confirmButton = {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        onEvent(GameScreenEvent.ResetGame)
                                    }) {
                                    Text("Yes")
                                }
                            },
                            title = {
                                Text(
                                    text = state.confirmDialogTitle ?: "Confirmation",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            text = {
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            })
                    }
                }

                if (imeState.value) {
                    Spacer(modifier = Modifier.height(300.dp))
                }
            }
        }
    }
}