package com.vixiloc.guessnumber.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_setting")
data class GameSetting(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val gameLevel: GameLevel = GameLevel.EASY,
)

enum class GameLevel {
    EASY,
    MEDIUM,
    HARD
}