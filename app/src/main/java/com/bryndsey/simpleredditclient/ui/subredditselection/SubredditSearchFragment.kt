package com.bryndsey.simpleredditclient.ui.subredditselection

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bryndsey.simpleredditclient.R
import kotlinx.android.synthetic.main.subreddit_search_view.*
import org.koin.android.viewmodel.ext.android.viewModel

@ContentView(R.layout.subreddit_search_view)
class SubredditSearchFragment : Fragment() {

    private val searchViewModel: SubredditSearchViewModel by viewModel()

    private val subredditSearchAdapter = SubredditOverviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subredditSearchResultsRecyclerView.adapter = subredditSearchAdapter

        searchViewModel.searchLiveData.observe(this, Observer {
            subredditSearchAdapter.setAdapterData(it)
        })

        subredditNameSearchBox.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    performSearch()
                    closeKeyboard()
                    true
                }
                else -> false
            }
        }
    }

    private fun performSearch() {
        val searchTerm = subredditNameSearchBox.text.toString()
        searchViewModel.performSearch(searchTerm)
    }

    private fun closeKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // TODO: Maybe don't call this at all if view is null?
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}