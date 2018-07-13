package com.bryndsey.simpleredditclient.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*
import ru.noties.markwon.Markwon

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        with(itemView) {
            reddit_post_title.text = redditPostData.title
            reddit_post_score.text = redditPostData.score.toString()
            reddit_post_comments.text = redditPostData.numComments.toString() + " comments"

            reddit_post_comments.setOnClickListener {
                Log.d("BRYAN", "Clicked comments. Opening url " + redditPostData.url)
            }

            Markwon.setMarkdown(reddit_post_text, redditPostData.text.orEmpty())

            reddit_post_text.visibility = View.GONE

            setOnClickListener {
                if (reddit_post_text.visibility == View.VISIBLE) {
                    reddit_post_text.visibility = View.GONE
                } else {
                    reddit_post_text.visibility = View.VISIBLE
                }
            }
        }
    }
}