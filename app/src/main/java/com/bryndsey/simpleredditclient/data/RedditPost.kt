package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.PostHintType

// TODO: Make most of these non-null
data class RedditPost(
        val title: String?,
        val text: String?,
        val score: Int?,
        val numComments: Int?,
        val url: String?,
        val id: String,
        val isSelf: Boolean,
        val createdDateMillis: Long?,
        val postHintType: PostHintType
)