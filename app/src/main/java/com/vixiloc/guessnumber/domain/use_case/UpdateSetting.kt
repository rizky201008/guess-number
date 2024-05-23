package com.vixiloc.guessnumber.domain.use_case

import com.vixiloc.guessnumber.domain.model.GameSetting
import com.vixiloc.guessnumber.domain.repository.GameSettingRepository

class UpdateSetting(private val repository: GameSettingRepository) {
    suspend operator fun invoke(data: GameSetting) {
        repository.update(data)
    }
}