package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RedditPostDetailsViewModel @Inject constructor(private val repository: RedditPostRepository) :
        ViewModel() {

    private var postRetrievalDisposable: Disposable? = null

    private val _postLiveData = MutableLiveData<RedditPost>()
    val postLiveData: LiveData<RedditPost>
        get() = _postLiveData

    fun initialize(postId: String) {
        if (postRetrievalDisposable == null) {
            postRetrievalDisposable = repository.getPostById(postId)
                    .subscribe(
                            { _postLiveData.postValue(it) },
                            { Log.e("BRYAN", "Error finding cached post", it) }
                    )
        }
    }

    override fun onCleared() {
        postRetrievalDisposable?.dispose()
        postRetrievalDisposable = null

        super.onCleared()
    }
}