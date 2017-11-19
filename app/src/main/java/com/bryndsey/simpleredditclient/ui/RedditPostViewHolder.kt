package com.bryndsey.simpleredditclient.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*
import ru.noties.markwon.Markwon

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        itemView.reddit_post_title.text = redditPostData.title
        itemView.reddit_post_score.text = redditPostData.score.toString()
        itemView.reddit_post_comments.text = redditPostData.numComments.toString() + " comments"
        itemView.reddit_post_comments.setOnClickListener { view ->
            Log.d("BRYAN", "Clicked comments. Opening url " + redditPostData.url)
        }
        Markwon.setMarkdown(itemView.reddit_post_text, redditPostData.text.orEmpty())
        itemView.setOnClickListener { view ->
            run {
                if (itemView.reddit_post_text.visibility == View.VISIBLE) {
                    itemView.reddit_post_text.visibility = View.GONE
                } else {
                    itemView.reddit_post_text.visibility = View.VISIBLE
                }
            }
        }
    }
}