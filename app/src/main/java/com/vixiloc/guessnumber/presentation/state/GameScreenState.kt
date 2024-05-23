package com.vixiloc.guessnumber.presentation.state

import androidx.compose.ui.text.input.TextFieldValue
import com.vixiloc.guessnumber.domain.model.GameSetting

data class GameScreenState(
    val setting: GameSetting? = null,
    val userAnswer: TextFieldValue = TextFieldValue(""),
    val answer: Int = 0,
    val attempts: Int = 3,
    val messageDialogText: String? = null,
    val messageDialogTitle: String? = null,
    val confirmDialogText: String? = null,
    val confirmDialogTitle: String? = null,
    val showConfirmDialog: Boolean = false,
    val showConfirmDialog1: Boolean = false,
    val showMessageDialog: Boolean = false,
    val answerCorrect: Boolean = false
)
