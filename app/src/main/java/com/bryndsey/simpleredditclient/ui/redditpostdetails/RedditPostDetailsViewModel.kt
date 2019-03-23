package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.Comment
import com.bryndsey.simpleredditclient.data.FetchCommentUseCase
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import io.reactivex.disposables.Disposable

class RedditPostDetailsViewModel(
        private val repository: RedditPostRepository,
        private val fetchCommentUseCase: FetchCommentUseCase) : ViewModel() {

    private var postRetrievalDisposable: Disposable? = null
    private var commentRetrievalDisposable: Disposable? = null

    private val _postLiveData = MutableLiveData<RedditPost>()
    val postLiveData: LiveData<RedditPost>
        get() = _postLiveData

    private val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData: LiveData<List<Comment>>
        get() = _commentsLiveData

    fun initialize(postId: String) {
        // TODO: Maybe find a better way to determine if this is already initialized?
        if (postRetrievalDisposable == null) {
            postRetrievalDisposable = repository.getPostById(postId)
                    .subscribe(
                            { _postLiveData.postValue(it) },
                            { Log.e("BRYAN", "Error finding cached post", it) }
                    )

            commentRetrievalDisposable = fetchCommentUseCase.fetchComments(postId)
                    .subscribe({
                        _commentsLiveData.postValue(it)
                    }, {
                        Log.e("BRYAN", "Error fetching comments", it)
                    })
        }
    }

    override fun onCleared() {
        // TODO: Maybe use a compositeDisposable to manage clearing these?
        postRetrievalDisposable?.dispose()
        postRetrievalDisposable = null

        commentRetrievalDisposable?.dispose()
        commentRetrievalDisposable = null

        super.onCleared()
    }
}