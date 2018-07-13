package com.bryndsey.simpleredditclient.di

import com.bryndsey.simpleredditclient.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}
