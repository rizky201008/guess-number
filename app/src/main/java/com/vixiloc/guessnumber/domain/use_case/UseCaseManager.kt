package com.vixiloc.guessnumber.domain.use_case

import com.vixiloc.guessnumber.domain.repository.GameSettingRepository
import com.vixiloc.guessnumber.domain.use_case.GetSetting
import com.vixiloc.guessnumber.domain.use_case.InsertSetting
import com.vixiloc.guessnumber.domain.use_case.UpdateSetting

class UseCaseManager(private val repository: GameSettingRepository) {
    fun getSetting() = GetSetting(repository = repository)
    fun insertSetting() = InsertSetting(repository = repository)
    fun updateSetting() = UpdateSetting(repository = repository)
    fun analyzeAnswer() = AnalyzeAnswer()
}