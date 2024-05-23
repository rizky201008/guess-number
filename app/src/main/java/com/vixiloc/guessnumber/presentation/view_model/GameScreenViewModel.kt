package com.vixiloc.guessnumber.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    fun onEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.ChangeAnswer -> {
                _state.value = state.value.copy(userAnswer = event.answer)
            }

            is GameScreenEvent.SubmitAnswer -> {
                submitAnswer()
            }

            is GameScreenEvent.ShowMessageDialog -> {
                _state.value =
                    state.value.copy(
                        showMessageDialog = true,
                        messageDialogTitle = event.title,
                        messageDialogText = event.message
                    )
            }

            is GameScreenEvent.ShowConfirmDialog -> {
                _state.value =
                    state.value.copy(
                        showConfirmDialog = true,
                        confirmDialogTitle = event.title,
                        confirmDialogText = event.message
                    )
            }

            is GameScreenEvent.ShowConfirmDialog1 -> {
                _state.value =
                    state.value.copy(
                        showConfirmDialog1 = true,
                        confirmDialogTitle = event.title,
                        confirmDialogText = event.message
                    )
            }

            is GameScreenEvent.DismissConfirmDialog -> {
                _state.value =
                    state.value.copy(
                        showConfirmDialog = false,
                        confirmDialogTitle = null,
                        confirmDialogText = null
                    )
            }

            is GameScreenEvent.DismissConfirmDialog1 -> {
                resetGame()
                _state.value =
                    state.value.copy(
                        showConfirmDialog1 = false,
                        confirmDialogText = null,
                        confirmDialogTitle = null,
                    )
            }

            is GameScreenEvent.DismissMessageDialog -> {
                _state.value =
                    state.value.copy(
                        showMessageDialog = false,
                        messageDialogText = null,
                        messageDialogTitle = null,
                        answerCorrect = false
                    )
            }

            is GameScreenEvent.ResetGame -> {
                resetGame()
            }

            is GameScreenEvent.UpdateSettings -> {
                getSetting()
                generateRandomNumber()
            }
        }
    }

    private fun resetGame() {
        generateRandomNumber()
        _state.value = state.value.copy(
            userAnswer = state.value.userAnswer.copy(text = ""),
            attempts = 3,
            showConfirmDialog = false,
            showConfirmDialog1 = false
        )
    }

    private fun attemptOver() {
        onEvent(
            GameScreenEvent.ShowConfirmDialog1(
                "Game Over",
                "You have no more attempts left, the correct answer is ${state.value.answer}. Would you want to try again?"
            )
        )
    }

    private fun getSetting() {
        getSettingUseCase().onEach { setting ->
            _state.value = state.value.copy(setting = setting)
        }.launchIn(viewModelScope)
    }

    private fun submitAnswer() {
        val userAnswer = state.value.userAnswer.text.toInt()
        val answer = state.value.answer
        if (state.value.attempts == 0) {
            attemptOver()
        } else {
            analyzeAnswerUseCase(userAnswer, answer).onEach { isCorrect ->
                if (isCorrect) {
                    generateRandomNumber()
                    onEvent(
                        GameScreenEvent.ShowMessageDialog(
                            "Congratulations!",
                            "Your answer is correct, answer is $answer!"
                        )
                    )
                    _state.value = state.value.copy(
                        answerCorrect = true
                    )
                } else {
                    onEvent(
                        GameScreenEvent.ShowMessageDialog(
                            "Try again",
                            "Your answer is incorrect!"
                        )
                    )
                    _state.value =
                        state.value.copy(
                            attempts = state.value.attempts - 1
                        )
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun generateRandomNumber() {
        state.value.setting?.let {
            when (it.gameLevel) {
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
        }
    }
}