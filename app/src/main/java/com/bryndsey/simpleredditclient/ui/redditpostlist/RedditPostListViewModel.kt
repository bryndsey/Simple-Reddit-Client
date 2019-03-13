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

    private val _postListLiveData = MutableLiveData<List<RedditPost>>()

    val postListLiveData: LiveData<List<RedditPost>>
        get() = _postListLiveData

    fun initialize(subredditName: String) {
        if (postFetchDisposable == null) {
            postFetchDisposable = redditPostRepository.fetchRedditPosts(subredditName)
                    .retry(2)
                    .onErrorReturn { emptyList() }
                    .subscribe({
                        _postListLiveData.postValue(it)
                    }, {
                        // TODO: Show an error state when this occurs
                        Log.e("BRYAN", "Error fetching posts for subreddit $subredditName", it)
                    })
        }
    }

    override fun onCleared() {
        postFetchDisposable?.dispose()
        postFetchDisposable = null

        super.onCleared()
    }
}