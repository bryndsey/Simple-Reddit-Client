package com.bryndsey.simpleredditclient.ui.redditpostlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost

class RedditPostListAdapter : RecyclerView.Adapter<RedditPostViewHolder>() {

    private var postList: List<RedditPost> = ArrayList()

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_post_overview_card, parent, false)
        return RedditPostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setAdapterData(posts: List<RedditPost>) {
        postList = posts
        notifyDataSetChanged()
    }
}