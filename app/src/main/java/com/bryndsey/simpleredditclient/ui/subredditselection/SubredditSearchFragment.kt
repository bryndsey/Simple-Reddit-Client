package com.bryndsey.simpleredditclient.ui.subredditselection

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.ContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.di.ViewModelFactory
import com.bryndsey.simpleredditclient.ui.BaseFragment
import kotlinx.android.synthetic.main.subreddit_search_view.*
import javax.inject.Inject

@ContentView(R.layout.subreddit_search_view)
class SubredditSearchFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<SubredditSearchViewModel>

    private lateinit var viewModel: SubredditSearchViewModel

    private val subredditSearchAdapter = SubredditOverviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentHolder.applicationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SubredditSearchViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subredditSearchResultsRecyclerView.adapter = subredditSearchAdapter

        viewModel.searchLiveData.observe(this, Observer{
            subredditSearchAdapter.setAdapterData(it)
        })

        subredditNameSearchBox.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    performSearch()
                    true
                }
                else -> false
            }
        }
    }

    private fun performSearch() {
        val searchTerm = subredditNameSearchBox.text.toString()
        viewModel.performSearch(searchTerm)
    }
}