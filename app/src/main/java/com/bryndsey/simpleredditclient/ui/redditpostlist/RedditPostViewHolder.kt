package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.network.RedditPost
import kotlinx.android.synthetic.main.reddit_post.view.*
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPost: RedditPost) {
        with(itemView) {
            reddit_post_title.text = redditPost.title
            reddit_post_score.text = redditPost.score.toString()
            reddit_post_comments.text = redditPost.numComments.toString() + " comments"

            val linkVisibility = if (redditPost.isSelf) View.INVISIBLE else View. VISIBLE
            reddit_post_link_indicator.visibility = linkVisibility

            reddit_post_comments.setOnClickListener {
                Log.d("BRYAN", "Clicked comments. Opening url " + redditPost.url)
            }

            setOnClickListener {
                if (redditPost.isSelf) {
                    openPostDetails(redditPost)
                } else {
                    val customTabsIntent = CustomTabsIntent.Builder()
                            .setToolbarColor(resources.getColor(R.color.colorPrimary))
                            .setShowTitle(true)
                            .build()

                    CustomTabsHelper.openCustomTab(itemView.context,
                            customTabsIntent,
                            Uri.parse(redditPost.url),
                            WebViewFallback())
                }
            }
        }
    }

    private fun openPostDetails(redditPost: RedditPost) {
        val bundle = Bundle()
        bundle.putString("postId", redditPost.id)
        itemView.findNavController().navigate(R.id.action_postSelected, bundle)
    }
}