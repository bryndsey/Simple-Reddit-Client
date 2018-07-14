package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.arch.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import com.bryndsey.simpleredditclient.network.RedditPostData
import io.reactivex.Single
import javax.inject.Inject

class RedditPostListViewModel @Inject constructor(private val redditPostRepository: RedditPostRepository) :
        ViewModel() {

    val redditPostObservable = redditPostRepository.fetchRedditPosts()
            .onErrorReturn { emptyList() }
            .cache()

    fun getRedditPosts(): Single<List<RedditPostData>> {
        return redditPostObservable
    }
}