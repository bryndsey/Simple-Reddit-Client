package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class ApiRedditResponseData(@SerializedName("children") val posts: List<ApiRedditPost>)