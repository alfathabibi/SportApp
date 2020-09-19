package org.d3if3049.sportapp

import android.app.Application
import org.d3if3049.sportapp.core.di.databaseModule
import org.d3if3049.sportapp.core.di.networkModule
import org.d3if3049.sportapp.core.di.repositoryModule
import org.d3if3049.sportapp.di.useCaseModule
import org.d3if3049.sportapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}