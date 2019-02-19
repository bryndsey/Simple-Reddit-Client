package com.bryndsey.simpleredditclient.ui.redditpostlist

import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import io.reactivex.Single
import javax.inject.Inject

class RedditPostListViewModel @Inject constructor(private val redditPostRepository: RedditPostRepository) :
        ViewModel() {

    // TODO: The caching here is broken. Update to use LiveData
    fun getRedditPosts(subredditName: String): Single<List<RedditPost>> {
        return redditPostRepository.fetchRedditPosts(subredditName)
                .onErrorReturn { emptyList() }
                .cache()
    }
}