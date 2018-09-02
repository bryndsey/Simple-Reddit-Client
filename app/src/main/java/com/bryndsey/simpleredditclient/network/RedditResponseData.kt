package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class RedditResponseData(@SerializedName("children") val posts: List<ApiRedditPost>)