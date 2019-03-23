package com.bryndsey.simpleredditclient.ui.redditpostdetails.comments

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Comment
import com.bryndsey.simpleredditclient.ui.TimeDisplayFormatter.getStringForTimeSince
import com.bryndsey.simpleredditclient.ui.toDisplayString
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.reddit_comment.view.*
import ru.noties.markwon.Markwon

class CommentItem(private val comment: Comment, private val commentDepth: Int) : Item(), ExpandableItem {

    private var expandableGroup: ExpandableGroup? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView

        // Remove all indicators in case of recycled view
        itemView.depthIndicatorHolder.removeAllViews()
        if (commentDepth > 0) {
            for (i in 1..commentDepth) {
                val indicatorView =
                        LayoutInflater.from(itemView.context).inflate(
                                R.layout.comment_depth_indicator,
                                itemView.depthIndicatorHolder,
                                false)

                itemView.depthIndicatorHolder.addView(indicatorView)
            }
        }

        Markwon.setMarkdown(itemView.commentText, comment.text.orEmpty())
        itemView.commentScore.text = comment.score?.toDisplayString()
        if (comment.createdDateMillis != null) {
            itemView.commentTimeDisplay.text = getStringForTimeSince(comment.createdDateMillis)
            itemView.commentTimeDisplay.visibility = VISIBLE
        } else {
            itemView.commentTimeDisplay.visibility = GONE
            itemView.commentTimeDisplay.text = null
        }
        itemView.setOnClickListener {
            expandableGroup?.onToggleExpanded()
        }
    }

    override fun getLayout() = R.layout.reddit_comment

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}