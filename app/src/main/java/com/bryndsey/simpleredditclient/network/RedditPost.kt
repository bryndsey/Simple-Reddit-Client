package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class RedditPost(@SerializedName("data") val data: RedditPostData)