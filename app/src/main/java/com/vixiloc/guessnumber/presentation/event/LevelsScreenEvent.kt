package com.vixiloc.guessnumber.presentation.event

import com.vixiloc.guessnumber.domain.model.Level

sealed class LevelsScreenEvent {
    data class SelectLevel(val data: Level) : LevelsScreenEvent()
    data object UpdateLevel : LevelsScreenEvent()
}