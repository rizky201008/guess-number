package com.vixiloc.guessnumber.di

import com.vixiloc.guessnumber.data.repository.GameSettingRepositoryImpl
import com.vixiloc.guessnumber.domain.repository.GameSettingRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GameSettingRepository> { GameSettingRepositoryImpl(get()) }
}