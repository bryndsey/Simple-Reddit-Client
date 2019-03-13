package com.bryndsey.simpleredditclient

import android.app.Application
import com.bryndsey.simpleredditclient.di.dataModule
import com.bryndsey.simpleredditclient.di.networkModule
import com.bryndsey.simpleredditclient.di.viewModelModule
import org.koin.android.ext.android.startKoin

class RedditClientApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
                this,
                listOf(networkModule, dataModule, viewModelModule)
        )
    }
}
