package com.bryndsey.simpleredditclient

import android.app.Application
import com.bryndsey.simpleredditclient.di.*
import org.koin.android.ext.android.startKoin

class RedditClientApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentHolder.applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(NetworkModule)
                .build()

        startKoin(
                this,
                listOf(networkModule, dataModule, viewModelModule)
        )
    }
}
