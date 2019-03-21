package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import io.reactivex.disposables.Disposable

class RedditPostListViewModel(private val redditPostRepository: RedditPostRepository) :
        ViewModel() {

    private var postFetchDisposable: Disposable? = null
    private var morePostFetchDisposable: Disposable? = null

    private val _postListLiveData = MutableLiveData<List<RedditPost>>()

    private lateinit var subredditName: String
    private lateinit var postList : MutableList<RedditPost>

    val postListLiveData: LiveData<List<RedditPost>>
        get() = _postListLiveData

    fun initialize(subredditName: String) {
        if (postFetchDisposable == null) {
            this.subredditName = subredditName
            postFetchDisposable = redditPostRepository.fetchRedditPosts(subredditName)
                    .retry(2)
                    .onErrorReturn { emptyList() }
                    .subscribe({
                        postList = it.toMutableList()
                        // Post immutable list so future updates won't mess with things
                        _postListLiveData.postValue(it)
                    }, {
                        // TODO: Show an error state when this occurs
                        Log.e("BRYAN", "Error fetching posts for subreddit $subredditName", it)
                    })
        }
    }

    fun loadMorePosts() {
        // Prevent attempts to load more if we're already in the middle of loading
        if (morePostFetchDisposable != null) return

        val lastPostFullname = postList.last().fullname
        morePostFetchDisposable = redditPostRepository.fetchMoreRedditPosts(subredditName, lastPostFullname)
                // FIXME: A lot of this is largely duplicated from the initial fetch
                .retry(2)
                .onErrorReturn { emptyList() }
                .doAfterTerminate {
                    // Reset disposable so we know it's safe to fetch more posts
                    // Note that the disposable should already be disposed by this point
                    morePostFetchDisposable = null
                }
                .subscribe({
                    postList.addAll(it)
                    _postListLiveData.postValue(postList.toList())
                }, {
                    // TODO: Show an error state when this occurs
                    Log.e("BRYAN", "Error fetching more posts for subreddit $subredditName", it)
                })
    }

    override fun onCleared() {
        postFetchDisposable?.dispose()
        postFetchDisposable = null

        morePostFetchDisposable?.dispose()

        super.onCleared()
    }
}