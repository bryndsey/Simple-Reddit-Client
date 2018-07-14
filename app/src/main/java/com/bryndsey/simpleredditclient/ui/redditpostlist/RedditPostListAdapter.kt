package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.network.RedditPostData

class RedditPostListAdapter : RecyclerView.Adapter<RedditPostViewHolder>() {

    var postList: List<RedditPostData> = ArrayList()

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_post, parent, false)
        return RedditPostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setAdapterData(posts: List<RedditPostData>) {
        postList = posts
    }
}