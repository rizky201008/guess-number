package com.vixiloc.guessnumber.domain.use_case

import com.vixiloc.guessnumber.domain.model.GameSetting
import com.vixiloc.guessnumber.domain.repository.GameSettingRepository
import kotlinx.coroutines.flow.Flow

class GetSetting(private val repository: GameSettingRepository) {
    operator fun invoke(): Flow<GameSetting?> {
        return repository.getSetting()
    }
}