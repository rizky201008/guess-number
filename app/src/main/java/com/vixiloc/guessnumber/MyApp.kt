package com.vixiloc.guessnumber

import android.app.Application
import com.vixiloc.guessnumber.di.daoModule
import com.vixiloc.guessnumber.di.repositoryModule
import com.vixiloc.guessnumber.di.useCaseModule
import com.vixiloc.guessnumber.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(daoModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}