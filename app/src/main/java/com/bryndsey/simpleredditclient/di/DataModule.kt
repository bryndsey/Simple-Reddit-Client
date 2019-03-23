package com.bryndsey.simpleredditclient.di

import com.bryndsey.simpleredditclient.data.FetchCommentUseCase
import com.bryndsey.simpleredditclient.data.RedditPostRepository
import com.bryndsey.simpleredditclient.data.SubredditSearchUseCase
import org.koin.dsl.module.module

val dataModule = module {
    single { RedditPostRepository(get()) }

    single { SubredditSearchUseCase(get()) }

    single { FetchCommentUseCase(get())}
}