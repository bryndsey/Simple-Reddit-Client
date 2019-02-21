package com.bryndsey.simpleredditclient.ui.subredditselection

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Subreddit
import kotlinx.android.synthetic.main.subreddit_overview_card.view.*

class SubredditOverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(subreddit: Subreddit) {
        itemView.subredditOverviewCardNameText.text = subreddit.prefixedName

        if (subreddit.shortDescription.isNullOrBlank()) {
            itemView.subredditOverviewCardDescriptionText.visibility = GONE
        } else {
            itemView.subredditOverviewCardDescriptionText.visibility = VISIBLE
        }
        itemView.subredditOverviewCardDescriptionText.text = subreddit.shortDescription

        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("subredditName", subreddit.name)
            itemView.findNavController().navigate(R.id.action_subredditSelected, bundle)
        }
    }
}