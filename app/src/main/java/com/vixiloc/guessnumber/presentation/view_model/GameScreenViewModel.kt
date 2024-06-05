package com.vixiloc.guessnumber.presentation.view_model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.guessnumber.domain.model.GameLevel
import com.vixiloc.guessnumber.domain.use_case.GetSetting
import com.vixiloc.guessnumber.domain.use_case.UseCaseManager
import com.vixiloc.guessnumber.presentation.event.GameScreenEvent
import com.vixiloc.guessnumber.presentation.state.GameScreenState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GameScreenViewModel(useCaseManager: UseCaseManager) : ViewModel() {
    val getSettingUseCase: GetSetting = useCaseManager.getSetting()
    val analyzeAnswerUseCase = useCaseManager.analyzeAnswer()
    private val _state = mutableStateOf(GameScreenState())
    val state: State<GameScreenState> = _state

    init {
        getSetting()
        generateRandomNumber()
    }

    fun onEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.ChangeAnswer -> {
                _state.value = state.value.copy(userAnswer = event.answer)
            }

            is GameScreenEvent.SubmitAnswer -> {
                submitAnswer()
            }

            is GameScreenEvent.ResetGame -> {
                resetGame()
            }

            is GameScreenEvent.ShowResetConfirmDialog -> {
                _state.value = state.value.copy(
                    showResetConfirmDialog = true,
                    resetTitle = "Reset Game",
                    resetMessage = "Are you sure you want to reset the game?"
                )
            }

            is GameScreenEvent.DismissResetConfirmDialog -> {
                _state.value = state.value.copy(
                    showResetConfirmDialog = false
                )
            }

            is GameScreenEvent.ConfirmResetConfirmDialog -> {
                resetGame()
            }

            is GameScreenEvent.ShowCorrectMessageDialog -> {
                _state.value = state.value.copy(
                    showCorrectMessageDialog = true,
                    correctTitle = "Correct",
                    correctMessage = "Your answer (${state.value.answer}) is correct!",
                    answerCorrect = true
                )
            }

            is GameScreenEvent.DismissCorrectMessageDialog -> {
                _state.value = state.value.copy(
                    showCorrectMessageDialog = false,
                    answerCorrect = false
                )
                resetGame()
            }

            is GameScreenEvent.ShowWrongMessageDialog -> {
                _state.value = state.value.copy(
                    showWrongMessageDialog = true,
                    wrongTitle = "Wrong",
                    wrongMessage = "Your answer is incorrect!"
                )
            }

            is GameScreenEvent.DismissWrongMessageDialog -> {
                _state.value = state.value.copy(
                    showWrongMessageDialog = false
                )
                resetInput()
            }

            is GameScreenEvent.ShowGameOverMessageDialog -> {
                _state.value = state.value.copy(
                    showGameOverMessageDialog = true,
                    gameOverTitle = "Game Over",
                    gameOverMessage = "You have no more attempts left, the correct answer is ${state.value.answer}. Would you want to try again?"
                )
            }

            is GameScreenEvent.DismissGameOverMessageDialog -> {
                _state.value = state.value.copy(
                    showGameOverMessageDialog = false
                )
                resetGame()
            }
        }
    }

    private fun resetGame() {
        generateRandomNumber()
        _state.value = state.value.copy(
            attempts = 3,
            showResetConfirmDialog = false
        )
        resetInput()
    }

    private fun resetInput() {
        _state.value = state.value.copy(
            userAnswer = TextFieldValue("")
        )
    }

    private fun getSetting() {
        getSettingUseCase().onEach { setting ->
            _state.value = state.value.copy(setting = setting)
        }.launchIn(viewModelScope)
    }

    private fun submitAnswer() {
        if (state.value.attempts == 0) {
            onEvent(GameScreenEvent.ShowGameOverMessageDialog)
        } else {
            if (state.value.userAnswer.text.isNotBlank()) {
                val answer = state.value.answer
                val userAnswer = state.value.userAnswer.text.toInt()
                analyzeAnswerUseCase(userAnswer, answer).onEach { isCorrect ->
                    if (isCorrect) {
                        onEvent(
                            GameScreenEvent.ShowCorrectMessageDialog
                        )
                    } else {
                        onEvent(
                            GameScreenEvent.ShowWrongMessageDialog
                        )
                        _state.value =
                            state.value.copy(
                                attempts = state.value.attempts - 1
                            )
                        _state.value =
                            state.value.copy(
                                attempts = state.value.attempts - 1
                            )
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun generateRandomNumber() {
        if (state.value.setting != null) {
            when (state.value.setting!!.gameLevel) {
                GameLevel.EASY -> {
                    _state.value = state.value.copy(answer = (1..10).random())
                }

                GameLevel.MEDIUM -> {
                    _state.value = state.value.copy(answer = (1..100).random())
                }

                GameLevel.HARD -> {
                    _state.value = state.value.copy(answer = (1..1000).random())
                }
            }
        } else {
            _state.value = state.value.copy(answer = (1..10).random())
        }


        Log.i(TAG, "Correct answer: ${state.value.answer}")
    }

}