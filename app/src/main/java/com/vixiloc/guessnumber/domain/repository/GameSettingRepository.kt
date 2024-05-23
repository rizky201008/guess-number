package com.vixiloc.guessnumber.domain.repository

import com.vixiloc.guessnumber.domain.model.GameSetting
import kotlinx.coroutines.flow.Flow

interface GameSettingRepository {
    fun getSetting(): Flow<GameSetting?>
    suspend fun insert(gameSetting: GameSetting)
    suspend fun update(gameSetting: GameSetting)
}