package com.bryndsey.simpleredditclient.network

import io.reactivex.Single
import retrofit2.http.GET

interface RedditService {

    @GET("r/AndroidDev/.json")
    fun getSubredditPosts(): Single<ApiRedditResponse>
}