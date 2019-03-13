package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost
import kotlinx.android.synthetic.main.reddit_post_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class RedditPostListFragment: Fragment() {

    private val adapter = RedditPostListAdapter()

    private val postListViewModel: RedditPostListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Define a better fallback if stuff is null
        val subredditName = arguments?.getString("subredditName") ?: ""
        postListViewModel.initialize(subredditName)

        post_list.adapter = adapter

        postListViewModel.postListLiveData.observe(viewLifecycleOwner, Observer {
            updatePosts(it)
        })
    }

    private fun updatePosts(postList: List<RedditPost>) {
        adapter.setAdapterData(postList)
    }
}