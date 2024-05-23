package com.vixiloc.guessnumber.di

import com.vixiloc.guessnumber.domain.use_case.UseCaseManager
import org.koin.dsl.module

val useCaseModule = module {
    single {
        UseCaseManager(repository = get())
    }
}