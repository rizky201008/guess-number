package com.vixiloc.guessnumber.data.repository

import com.vixiloc.guessnumber.data.dao.GameSettingDao
import com.vixiloc.guessnumber.domain.model.GameSetting
import com.vixiloc.guessnumber.domain.repository.GameSettingRepository
import kotlinx.coroutines.flow.Flow

class GameSettingRepositoryImpl(private val gameSettingDao: GameSettingDao) :
    GameSettingRepository {
    override fun getSetting(): Flow<GameSetting?> {
        return gameSettingDao.getSetting()
    }

    override suspend fun insert(gameSetting: GameSetting) {
        gameSettingDao.insert(gameSetting)
    }

    override suspend fun update(gameSetting: GameSetting) {
        gameSettingDao.update(gameSetting)
    }
}