package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.RedditService
import com.bryndsey.simpleredditclient.network.toSubreddit
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SubredditSearchUseCase(private val redditService: RedditService) {

    fun searchSubreddits(searchTerm: String): Single<List<Subreddit>> {
        return redditService.searchSubreddits(searchTerm)
                .subscribeOn(Schedulers.io())
                .map { subredditList ->
                    subredditList.data.posts.map {
                        it.data.toSubreddit()
                    }
                }
    }
}