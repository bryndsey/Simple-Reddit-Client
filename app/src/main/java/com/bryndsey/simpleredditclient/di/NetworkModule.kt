package com.bryndsey.simpleredditclient.di

import com.bryndsey.simpleredditclient.network.ApiRedditResponse
import com.bryndsey.simpleredditclient.network.ApiRedditResponseSerializer
import com.bryndsey.simpleredditclient.network.RedditService
import com.google.gson.GsonBuilder
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val gson = GsonBuilder()
        .registerTypeAdapter(ApiRedditResponse::class.java, ApiRedditResponseSerializer())
        .create()

private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

val networkModule = module {
    single {
        retrofit.create(RedditService::class.java)
    }
}
