package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        with(itemView) {
            reddit_post_title.text = redditPostData.title
            reddit_post_score.text = redditPostData.score.toString()
            reddit_post_comments.text = redditPostData.numComments.toString() + " comments"

            reddit_post_comments.setOnClickListener {
                Log.d("BRYAN", "Clicked comments. Opening url " + redditPostData.url)
            }

            setOnClickListener { findNavController().navigate(R.id.action_postSelected) }
        }
    }
}