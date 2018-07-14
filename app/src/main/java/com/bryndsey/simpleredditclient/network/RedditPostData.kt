package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName


data class RedditPostData(
        @SerializedName("title") val title: String?,
        @SerializedName("selftext") val text: String?,
        @SerializedName("score") val score: Int?,
        @SerializedName("num_comments") val numComments: Int?,
        @SerializedName("url") val url: String?,
        @SerializedName("id") val id: String)