package com.bryndsey.simpleredditclient.ui.redditpostdetails.comments

import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Comment
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.reddit_comment.view.*

class CommentItem(private val comment: Comment) : Item(), ExpandableItem {

    private var expandableGroup: ExpandableGroup? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.commentText.text = comment.text
        viewHolder.itemView.commentScore.text = comment.score.toString()
        viewHolder.itemView.setOnClickListener {
            expandableGroup?.onToggleExpanded()
        }
    }

    override fun getLayout() = R.layout.reddit_comment

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}