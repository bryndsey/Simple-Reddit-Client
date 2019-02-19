package com.bryndsey.simpleredditclient.di

import com.bryndsey.simpleredditclient.ui.redditpostdetails.RedditPostDetailsFragment
import com.bryndsey.simpleredditclient.ui.redditpostlist.RedditPostListFragment
import com.bryndsey.simpleredditclient.ui.subredditselection.SubredditSearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ApplicationComponent {

    fun inject(redditPostListFragment: RedditPostListFragment)

    fun inject(redditPostDetailsFragment: RedditPostDetailsFragment)

    fun inject(subredditSearchFragment: SubredditSearchFragment)
}
