package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.RedditService
import com.bryndsey.simpleredditclient.network.toSubreddit
import io.reactivex.Single
import javax.inject.Inject

class SubredditSearchUseCase @Inject constructor(val redditService: RedditService) {

    fun searchSubreddits(searchTerm: String): Single<List<Subreddit>> {
        return redditService.searchSubreddits(searchTerm)
                .map { subredditList ->
                    subredditList.data.posts.map {
                        it.data.toSubreddit()
                    }
                }
    }
}