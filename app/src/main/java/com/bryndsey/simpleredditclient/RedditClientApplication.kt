package com.bryndsey.simpleredditclient

import android.app.Application
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.di.DaggerApplicationComponent
import com.bryndsey.simpleredditclient.di.NetworkModule

class RedditClientApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentHolder.applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(NetworkModule)
                .build()
    }
}
