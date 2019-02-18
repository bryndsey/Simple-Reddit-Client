package com.bryndsey.simpleredditclient.network

import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.Subreddit

fun ApiRedditListing.toRedditPost(): RedditPost =
        RedditPost(
                title,
                text,
                score,
                numComments,
                url,
                id,
                isSelf)

fun ApiRedditListing.toSubreddit(): Subreddit =
        Subreddit(
                displayName,
                displayNamePrefixed,
                subscriberCount)
