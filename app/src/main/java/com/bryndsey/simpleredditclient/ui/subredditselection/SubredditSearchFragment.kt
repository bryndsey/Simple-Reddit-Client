package com.bryndsey.simpleredditclient.ui.subredditselection

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.ContentView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.SubredditSearchUseCase
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.subreddit_search_view.*
import javax.inject.Inject

@ContentView(R.layout.subreddit_search_view)
class SubredditSearchFragment : BaseFragment() {

    @Inject
    lateinit var subredditSearchUseCase: SubredditSearchUseCase

    private val subredditSearchAdapter = SubredditOverviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentHolder.applicationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subredditSearchResultsRecyclerView.adapter = subredditSearchAdapter

        subredditPerformSearchButton.setOnClickListener { performSearch() }
    }

    private fun performSearch() {
        val searchTerm = subredditNameSearchBox.text.toString()
        val disposable = subredditSearchUseCase.searchSubreddits(searchTerm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { subredditSearchAdapter.setAdapterData(it) },
                        { Log.e("BRYAN", "error occurred searching for subreddits. Try again later.", it) })

        addSubscription(disposable)
    }
}