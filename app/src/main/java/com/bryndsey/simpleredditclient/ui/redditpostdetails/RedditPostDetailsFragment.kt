package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Comment
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.ui.TimeDisplayFormatter
import com.bryndsey.simpleredditclient.ui.redditpostdetails.comments.CommentItem
import com.bryndsey.simpleredditclient.ui.toDisplayString
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.reddit_post_details.*
import kotlinx.android.synthetic.main.reddit_post_overview.*
import org.koin.android.viewmodel.ext.android.getViewModel
import ru.noties.markwon.Markwon

class RedditPostDetailsFragment: Fragment() {

    private val testCommentList = listOf(
            Comment("Test Comment 1", 1),
            Comment("Test Comment 2", 10),
            Comment("Test Comment 3", 100),
            Comment("Test Comment 4", 1000)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = getViewModel<RedditPostDetailsViewModel>()

        val postId = arguments?.getString("postId") ?: ""
        viewModel.initialize(postId)

        viewModel.postLiveData.observe(viewLifecycleOwner, Observer {
            updateViewFromPost(it)
        })

        val groupAdapter = GroupAdapter<ViewHolder>()
        val commentItems = testCommentList.map { CommentItem(it) }

        val commentSection = Section(commentItems)
        val testExpandableGroup = ExpandableGroup(commentItems[0])
        testExpandableGroup.addAll(commentItems)
//        groupAdapter.addAll(commentItems)
        groupAdapter.add(testExpandableGroup)
        redditPostComments.adapter = groupAdapter
    }

    private fun updateViewFromPost(redditPost: RedditPost) {
        reddit_post_title.text = redditPost.title
        reddit_post_score.text = redditPost.score?.toDisplayString()
        reddit_post_comments.text = redditPost.numComments.toString()
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