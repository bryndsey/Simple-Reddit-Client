package com.bryndsey.simpleredditclient.network

import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.data.Subreddit
import com.bryndsey.simpleredditclient.data.TimeConstants

fun ApiRedditListing.toRedditPost(): RedditPost =
        RedditPost(
                title,
                text,
                score,
                numComments,
                url,
                id,
                isSelf,
                createdTimeUtcSeconds?.times(TimeConstants.MILLIS_PER_SECOND))

fun ApiRedditListing.toSubreddit(): Subreddit =
        Subreddit(
                displayName,
                displayNamePrefixed,
                shortDescription,
                subscriberCount)
