package com.bryndsey.simpleredditclient.ui.redditpostlist

import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import com.bryndsey.simpleredditclient.data.RedditPost
import io.reactivex.Single
import javax.inject.Inject

class RedditPostListViewModel @Inject constructor(private val redditPostRepository: RedditPostRepository) :
        ViewModel() {

    val redditPostObservable = redditPostRepository.fetchRedditPosts()
            .onErrorReturn { emptyList() }
            .cache()

    fun getRedditPosts(): Single<List<RedditPost>> {
        return redditPostObservable
    }
}