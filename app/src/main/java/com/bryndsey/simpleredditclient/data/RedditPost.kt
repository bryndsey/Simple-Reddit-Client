package com.bryndsey.simpleredditclient.data

data class RedditPost(
        val title: String?,
        val text: String?,
        val score: Int?,
        val numComments: Int?,
        val url: String?,
        val id: String,
        val isSelf: Boolean
)