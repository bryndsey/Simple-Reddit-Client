package com.bryndsey.simpleredditclient.network

fun ApiRedditPostData.toRedditPost(): RedditPostData =
        RedditPostData(
                title,
                text,
                score,
                numComments,
                url,
                id,
                isSelf)
