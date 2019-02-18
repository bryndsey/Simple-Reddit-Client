package com.bryndsey.simpleredditclient.ui.subredditselection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bryndsey.simpleredditclient.data.Subreddit
import kotlinx.android.synthetic.main.subreddit_overview_card.view.*

class SubredditOverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(subreddit: Subreddit) {
        itemView.subredditOverviewCardNameText.text = subreddit.prefixedName
        itemView.subredditOverviewCardDescriptionText.text = subreddit.shortDescription
    }
}