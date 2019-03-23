package com.bryndsey.simpleredditclient.di

import com.bryndsey.simpleredditclient.ui.redditpostdetails.RedditPostDetailsViewModel
import com.bryndsey.simpleredditclient.ui.redditpostlist.RedditPostListViewModel
import com.bryndsey.simpleredditclient.ui.subredditselection.SubredditSearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { RedditPostListViewModel(get()) }

    viewModel { RedditPostDetailsViewModel(get(), get()) }

    viewModel { SubredditSearchViewModel(get()) }
}