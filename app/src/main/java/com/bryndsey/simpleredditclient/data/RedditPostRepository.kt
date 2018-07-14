package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.RedditPostData
import com.bryndsey.simpleredditclient.network.RedditService
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditPostRepository @Inject constructor(val redditService: RedditService) {

    private val postDataSet = mutableSetOf<RedditPostData>()

    fun fetchRedditPosts(): Single<List<RedditPostData>> {
        return redditService.getSubredditPosts()
                .retry()
                .map {
                    it.data.posts.map { post-> post.data }
                }
                .doOnSuccess {
                    postDataSet.addAll(it)
                }
                .subscribeOn(Schedulers.io())
    }

    // TODO: Figure out if null should be allowed
    fun getPostById(id: String?): Maybe<RedditPostData> {
        val post = postDataSet.find { it.id == id }

        return if (post == null) Maybe.empty() else Maybe.just(post)
    }
}