package com.bryndsey.simpleredditclient.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("r/AndroidDev/.json")
    fun getSubredditPosts(): Single<ApiRedditResponse>

    @GET("subreddits/search.json")
    fun searchSubreddits(@Query("q") subredditName: String): Single<ApiRedditResponse>
}