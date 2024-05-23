package com.vixiloc.guessnumber.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vixiloc.guessnumber.presentation.state.HomeScreenState
import com.vixiloc.guessnumber.domain.use_case.UseCaseManager

class HomeScreenViewModel(useCaseManager: UseCaseManager) : ViewModel() {
    val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state
}