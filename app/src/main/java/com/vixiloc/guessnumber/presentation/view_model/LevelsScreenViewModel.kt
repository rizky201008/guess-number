package com.vixiloc.guessnumber.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.guessnumber.domain.model.GameSetting
import com.vixiloc.guessnumber.presentation.event.LevelsScreenEvent
import com.vixiloc.guessnumber.presentation.state.LevelsScreenState
import com.vixiloc.guessnumber.domain.use_case.UseCaseManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LevelsScreenViewModel(useCaseManager: UseCaseManager) : ViewModel() {

    private val _state = mutableStateOf(LevelsScreenState())
    val state: State<LevelsScreenState> = _state
    val getLevelsUseCase = useCaseManager.getSetting()
    val insertLevelUseCase = useCaseManager.insertSetting()
    val updateLevelUseCase = useCaseManager.updateSetting()

    init {
        getLevel()
    }

    fun onEvent(event: LevelsScreenEvent) {
        when (event) {
            is LevelsScreenEvent.SelectLevel -> {
                _state.value = state.value.copy(selectedLevel = event.data)
            }

            is LevelsScreenEvent.UpdateLevel -> {
                if (state.value.setting == null) {
                    insertLevel()
                } else {
                    updateLevel()
                }
            }
        }
    }

    private fun getLevel() {
        getLevelsUseCase().onEach { level ->
            _state.value = state.value.copy(
                setting = level, selectedLevel = level?.let {
                    state.value.levels.find { level -> level.level == it.gameLevel }
                } ?: state.value.selectedLevel
            )
        }.launchIn(viewModelScope)
    }

    private fun insertLevel() {
        val selectedLevel = state.value.selectedLevel
        val data = GameSetting(gameLevel = selectedLevel.level, id = 1)

        viewModelScope.launch {
            insertLevelUseCase(data)
            getLevel()
        }
    }

    private fun updateLevel() {
        val selectedLevel = state.value.selectedLevel
        val data = GameSetting(gameLevel = selectedLevel.level, id = 1)

        viewModelScope.launch {
            updateLevelUseCase(data)
            getLevel()
        }
    }
}