package com.bryndsey.simpleredditclient.ui.subredditselection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Subreddit

class SubredditOverviewAdapter : RecyclerView.Adapter<SubredditOverviewViewHolder>() {

    private var subredditList: List<Subreddit> = ArrayList()

    override fun onBindViewHolder(holder: SubredditOverviewViewHolder, position: Int) {
        holder.bind(subredditList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubredditOverviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subreddit_overview_card, parent, false)
        return SubredditOverviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subredditList.size
    }

    fun setAdapterData(subreddits: List<Subreddit>) {
        subredditList = subreddits
        notifyDataSetChanged()
    }
}