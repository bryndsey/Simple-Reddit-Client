package com.bryndsey.simpleredditclient.network

import retrofit2.Call
import retrofit2.http.GET

interface RedditService {

    @GET("r/AndroidDev/.json")
    fun getSubredditPosts(): Call<RedditResponse>
}