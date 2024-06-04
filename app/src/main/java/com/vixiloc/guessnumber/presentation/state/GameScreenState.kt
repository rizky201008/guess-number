package com.vixiloc.guessnumber.presentation.state

import androidx.compose.ui.text.input.TextFieldValue
import com.vixiloc.guessnumber.domain.model.GameSetting

data class GameScreenState(
    val setting: GameSetting? = null,
    val userAnswer: TextFieldValue = TextFieldValue(""),
    val answer: Int = 0,
    val attempts: Int = 3,
    val answerCorrect: Boolean = false,
    val showResetConfirmDialog: Boolean = false,
    val resetTitle: String = "Reset Game",
    val resetMessage: String = "",
    val showCorrectMessageDialog: Boolean = false,
    val correctTitle: String = "Correct",
    val correctMessage: String = "",
    val showWrongMessageDialog: Boolean = false,
    val wrongTitle: String = "Wrong",
    val wrongMessage: String = "",
    val showGameOverMessageDialog: Boolean = false,
    val gameOverTitle: String = "Game Over",
    val gameOverMessage: String = ""
)
