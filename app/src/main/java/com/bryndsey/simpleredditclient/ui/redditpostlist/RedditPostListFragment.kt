package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost
import kotlinx.android.synthetic.main.reddit_post_list.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val reloadBufferThreshold = 4

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

        val layoutManager = LinearLayoutManager(context)
        post_list.layoutManager = layoutManager
        
        // TODO: This could potentially be replaced with Paging library stuff
        // but it seemed overly complicated when investigating, so I just rolled my own stuff
        post_list.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // If we're almost at the bottom (i.e. the index of last visible item is close to
                // the last index within a certain threshold), then attempt to load more items
                //
                // NOTE: This is not at all optimized, and it very much assumes that the ViewModel
                // will ignore request spamming appropriately. I'm not really sure if this is a bad
                // idea long term or not.
                val newLastPositionIndex = layoutManager.findLastVisibleItemPosition()
                if (newLastPositionIndex > layoutManager.itemCount - reloadBufferThreshold) {
                    postListViewModel.loadMorePosts()
                }
            }
        })

        postListViewModel.postListLiveData.observe(viewLifecycleOwner, Observer {
            updatePosts(it)
        })
    }

    private fun updatePosts(postList: List<RedditPost>) {
        adapter.setAdapterData(postList)
    }
}