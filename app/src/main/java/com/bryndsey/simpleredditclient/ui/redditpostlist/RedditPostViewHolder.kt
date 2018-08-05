package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post.view.*
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPostData: RedditPostData) {
        with(itemView) {
            reddit_post_title.text = redditPostData.title
            reddit_post_score.text = redditPostData.score.toString()
            reddit_post_comments.text = redditPostData.numComments.toString() + " comments"

            reddit_post_comments.setOnClickListener {
                Log.d("BRYAN", "Clicked comments. Opening url " + redditPostData.url)
            }

            setOnClickListener {
                if (redditPostData.isSelf) {
                    openPostDetails(redditPostData)
                } else {
                    val cusomtTabsIntent = CustomTabsIntent.Builder().build()

                    CustomTabsHelper.openCustomTab(itemView.context,
                            cusomtTabsIntent,
                            Uri.parse(redditPostData.url),
                            WebViewFallback())
                }
            }
        }
    }

    private fun openPostDetails(redditPostData: RedditPostData) {
        val bundle = Bundle()
        bundle.putString("postId", redditPostData.id)
        itemView.findNavController().navigate(R.id.action_postSelected, bundle)
    }
}