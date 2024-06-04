package com.vixiloc.guessnumber.presentation.event

import androidx.compose.ui.text.input.TextFieldValue

sealed class GameScreenEvent {
    data class ChangeAnswer(val answer: TextFieldValue) : GameScreenEvent()
    data object SubmitAnswer : GameScreenEvent()
    data object ResetGame : GameScreenEvent()
    data object ShowResetConfirmDialog : GameScreenEvent()
    data object DismissResetConfirmDialog : GameScreenEvent()
    data object ConfirmResetConfirmDialog : GameScreenEvent()
    data object ShowCorrectMessageDialog : GameScreenEvent()
    data object DismissCorrectMessageDialog : GameScreenEvent()
    data object ShowWrongMessageDialog : GameScreenEvent()
    data object DismissWrongMessageDialog : GameScreenEvent()
    data object ShowGameOverMessageDialog : GameScreenEvent()
    data object DismissGameOverMessageDialog : GameScreenEvent()
}