package com.bryndsey.simpleredditclient.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        itemView.reddit_post_title.text = redditPostData.title
        itemView.reddit_post_text.text = redditPostData.text
        itemView.reddit_post_score.text = redditPostData.score.toString()
    }
}