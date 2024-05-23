package com.vixiloc.guessnumber.di

import com.vixiloc.guessnumber.presentation.view_model.GameScreenViewModel
import com.vixiloc.guessnumber.presentation.view_model.HomeScreenViewModel
import com.vixiloc.guessnumber.presentation.view_model.LevelsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GameScreenViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { LevelsScreenViewModel(get()) }
}