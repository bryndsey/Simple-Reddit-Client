package com.bryndsey.simpleredditclient.ui.favoritesubreddits

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.Subreddit
import com.bryndsey.simpleredditclient.ui.BaseFragment
import com.bryndsey.simpleredditclient.ui.subredditselection.SubredditOverviewAdapter
import kotlinx.android.synthetic.main.favorite_subreddit_view.*

@ContentView(R.layout.favorite_subreddit_view)
class FavoriteSubredditFragment : BaseFragment() {

    val testFavoriteSubredditList = listOf<Subreddit>(
            Subreddit("AndroidDev", "r/AndroidDev", "Fake description", 100),
            Subreddit("DadJokes", "r/DadJokes", "Fake description", 101),
            Subreddit("Gaming", "r/Gaming", "Fake description", 102),
            Subreddit("Pics", "r/Pics", "Fake description", 102),
            Subreddit("Gifs", "r/gifs", "Fake description", 102)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteSubredditRecyclerView.adapter = SubredditOverviewAdapter().apply {
            setAdapterData(testFavoriteSubredditList)
        }

    }
}