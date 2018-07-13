package com.bryndsey.simpleredditclient.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.network.RedditPostData
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.reddit_post_list.*
import javax.inject.Inject

class RedditPostListFragment: Fragment() {

    private lateinit var adapter: RedditPostAdapter

    @Inject lateinit var redditPostRepository: RedditPostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentHolder.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = RedditPostAdapter()
        post_list.adapter = adapter

        redditPostRepository.fetchRedditPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ postList -> updatePosts(postList) },
                        { Toast.makeText(context, "Error occurred fetching posts", Toast.LENGTH_SHORT).show() }
                )
    }

    private fun updatePosts(postList: List<RedditPostData>) {
        adapter.postList = postList
        adapter.notifyDataSetChanged()
    }
}