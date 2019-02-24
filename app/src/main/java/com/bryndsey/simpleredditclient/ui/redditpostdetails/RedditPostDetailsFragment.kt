package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.ui.BaseFragment
import com.bryndsey.simpleredditclient.ui.TimeDisplayFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.reddit_post_details.*
import kotlinx.android.synthetic.main.reddit_post_overview.*
import ru.noties.markwon.Markwon
import javax.inject.Inject

class RedditPostDetailsFragment: BaseFragment() {

    @Inject lateinit var repository: RedditPostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentHolder.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val disposable = repository.getPostById(arguments?.getString("postId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { updateViewFromPost(it) },
                        { Log.e("BRYAN", "Error finding cached post", it) }
                )

        addSubscription(disposable)
    }

    private fun updateViewFromPost(redditPost: RedditPost) {
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

        reddit_post_comments.setOnClickListener {
            Log.d("BRYAN", "Clicked comments. Opening url " + redditPost.url)
        }

        Markwon.setMarkdown(reddit_post_text, redditPost.text.orEmpty())
    }
}