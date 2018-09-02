package com.bryndsey.simpleredditclient.network

import com.bryndsey.simpleredditclient.data.RedditPost

fun ApiRedditPostData.toRedditPost(): RedditPost =
        RedditPost(
                title,
                text,
                score,
                numComments,
                url,
                id,
                isSelf)
