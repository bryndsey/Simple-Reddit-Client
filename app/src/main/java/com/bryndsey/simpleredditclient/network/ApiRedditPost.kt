package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class ApiRedditPost(@SerializedName("data") val data: ApiRedditPostData)