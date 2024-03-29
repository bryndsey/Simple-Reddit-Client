package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.ApiRedditResponse
import com.bryndsey.simpleredditclient.network.RedditService
import com.bryndsey.simpleredditclient.network.toRedditPost
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RedditPostRepository(private val redditService: RedditService) {

    private val postDataSet = mutableSetOf<RedditPost>()

    fun fetchRedditPosts(subredditName: String): Single<List<RedditPost>> {
        return redditService.getSubredditPosts(subredditName)
                .compose(this::handleRedditPostFetch)
    }

    fun fetchMoreRedditPosts(subredditName: String, lastPostId: String): Single<List<RedditPost>> {
        return redditService.getSubredditPosts(subredditName, lastPostId)
                .compose(this::handleRedditPostFetch)
    }

    private fun handleRedditPostFetch(fetchSingle: Single<ApiRedditResponse>): Single<List<RedditPost>> {
        return fetchSingle
                .retry(2)
                .map {
                    it.data.posts.map { post -> post.data.toRedditPost() }
                }
                .doOnSuccess {
                    postDataSet.addAll(it)
                }
                .subscribeOn(Schedulers.io())
    }

    fun getPostById(id: String?): Maybe<RedditPost> {
        val post = postDataSet.find { it.id == id }

        return if (post == null) Maybe.empty() else Maybe.just(post)
    }
}