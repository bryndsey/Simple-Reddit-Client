package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.ui.TimeDisplayFormatter
import kotlinx.android.synthetic.main.reddit_post_overview.view.*
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

class RedditPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(redditPost: RedditPost) {
        with(itemView) {
            reddit_post_title.text = redditPost.title
            reddit_post_score.text = redditPost.score.toString()
            reddit_post_comments.text = redditPost.numComments.toString() + " comments"
            if (redditPost.createdDateMillis != null) {
                reddit_post_creation_time.text = TimeDisplayFormatter.getStringForTimeSince(redditPost.createdDateMillis)
                reddit_post_creation_time.visibility = View.VISIBLE
            } else {
                reddit_post_creation_time.visibility = View.GONE
                reddit_post_creation_time.text = null
            }

            val linkVisibility = if (redditPost.isSelf) View.INVISIBLE else View.VISIBLE
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