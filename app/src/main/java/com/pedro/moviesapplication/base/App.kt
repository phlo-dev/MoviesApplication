package com.pedro.moviesapplication.base

import androidx.multidex.MultiDexApplication
import com.pedro.di.*
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                presentationModule,
                domainModule,
                dataModule,
                dataLocalModule,
                dataRemoteModule,
                remoteWebServicesModule
            )
        )
    }
}