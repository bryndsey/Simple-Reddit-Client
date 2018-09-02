package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName


data class RedditPost(
        @SerializedName("title") val title: String?,
        @SerializedName("selftext") val text: String?,
        @SerializedName("score") val score: Int?,
        @SerializedName("num_comments") val numComments: Int?,
        @SerializedName("url") val url: String?,
        @SerializedName("id") val id: String,
        @SerializedName("is_self") val isSelf: Boolean
)