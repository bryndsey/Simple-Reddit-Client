package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.network.RedditPostData
import kotlinx.android.synthetic.main.reddit_post_details.*
import ru.noties.markwon.Markwon

class RedditPostDetailsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateViewFromPost(RedditPostData(
                title = "Test Title",
                text = "This is some test text. I hope it works properly...",
                score = 1337,
                numComments = 42,
                url = null)
        )
    }

    private fun updateViewFromPost(redditPostData: RedditPostData) {
        reddit_post_title.text = redditPostData.title
        reddit_post_score.text = redditPostData.score.toString()
        reddit_post_comments.text = redditPostData.numComments.toString() + " comments"

        reddit_post_comments.setOnClickListener {
            Log.d("BRYAN", "Clicked comments. Opening url " + redditPostData.url)
        }

        Markwon.setMarkdown(reddit_post_text, redditPostData.text.orEmpty())
    }
}