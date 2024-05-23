package com.vixiloc.guessnumber.di

import com.vixiloc.guessnumber.data.GuessNumberDb
import com.vixiloc.guessnumber.data.dao.GameSettingDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single<GameSettingDao> {
        GuessNumberDb.getDatabase(androidContext()).gameDao()
    }
}