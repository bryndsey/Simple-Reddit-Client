package com.bryndsey.simpleredditclient.network

fun ApiRedditPostData.toRedditPost(): RedditPost =
        RedditPost(
                title,
                text,
                score,
                numComments,
                url,
                id,
                isSelf)
