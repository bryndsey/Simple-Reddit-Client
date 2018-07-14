package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.RedditPostData
import com.bryndsey.simpleredditclient.network.RedditService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditPostRepository @Inject constructor(val redditService: RedditService) {

    fun fetchRedditPosts(): Single<List<RedditPostData>> {
        return redditService.getSubredditPosts()
                .retry()
                .map { it.data.posts }
                .flatMapObservable { Observable.fromIterable(it) }
                .map { it.data }
                .toList()
                .subscribeOn(Schedulers.io())
    }
}