package com.bryndsey.simpleredditclient.ui.subredditselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryndsey.simpleredditclient.data.Subreddit
import com.bryndsey.simpleredditclient.data.SubredditSearchUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SubredditSearchViewModel
@Inject constructor(private val subredditSearchUseCase: SubredditSearchUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchLiveData = MutableLiveData<List<Subreddit>>()
    val searchLiveData: LiveData<List<Subreddit>>
        get() = _searchLiveData

    fun performSearch(searchTerm: String) {

        val disposable = subredditSearchUseCase.searchSubreddits(searchTerm)
                .subscribe({
                    _searchLiveData.postValue(it)
                }, {
                    // TODO: Post a proper error state instead
                    _searchLiveData.postValue(emptyList())
                })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}