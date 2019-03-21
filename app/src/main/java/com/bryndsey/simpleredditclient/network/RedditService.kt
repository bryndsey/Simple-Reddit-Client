package com.bryndsey.simpleredditclient.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("r/{subredditName}/.json")
    fun getSubredditPosts(
            @Path("subredditName") subredditName: String,
            @Query("after") lastFetchedPostFullname: String? = null): Single<ApiRedditResponse>

    @GET("subreddits/search.json")
    fun searchSubreddits(@Query("q") subredditName: String): Single<ApiRedditResponse>
}