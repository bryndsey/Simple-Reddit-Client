package com.bryndsey.simpleredditclient.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*
import ru.noties.markwon.Markwon

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        itemView.reddit_post_title.text = redditPostData.title
        Markwon.setMarkdown(itemView.reddit_post_text, redditPostData.text.orEmpty())
        itemView.reddit_post_score.text = redditPostData.score.toString()
    }
}