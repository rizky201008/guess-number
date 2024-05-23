package com.vixiloc.guessnumber.presentation.state

import com.vixiloc.guessnumber.domain.model.GameLevel
import com.vixiloc.guessnumber.domain.model.GameSetting
import com.vixiloc.guessnumber.domain.model.Level

data class LevelsScreenState(
    val setting: GameSetting? = null,
    val selectedLevel: Level = Level("Easy", GameLevel.EASY, "between 1-10"),
    val levels: List<Level> = listOf(
        Level("Easy", GameLevel.EASY, "between 1-10"),
        Level("Medium", GameLevel.MEDIUM, "between 1-100"),
        Level("Hard", GameLevel.HARD, "between 1-1000")
    )
)
