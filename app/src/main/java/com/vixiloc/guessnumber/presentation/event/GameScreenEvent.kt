package com.vixiloc.guessnumber.presentation.event

import androidx.compose.ui.text.input.TextFieldValue

sealed class GameScreenEvent {
    data class ChangeAnswer(val answer: TextFieldValue) : GameScreenEvent()
    data object SubmitAnswer : GameScreenEvent()
    data class ShowMessageDialog(val title: String, val message: String) : GameScreenEvent()
    data class ShowConfirmDialog(val title: String, val message: String) : GameScreenEvent()
    data class ShowConfirmDialog1(val title: String, val message: String) : GameScreenEvent()
    data object DismissMessageDialog : GameScreenEvent()
    data object DismissConfirmDialog : GameScreenEvent()
    data object DismissConfirmDialog1 : GameScreenEvent()
    data object ResetGame : GameScreenEvent()
    data object UpdateSettings : GameScreenEvent()
}