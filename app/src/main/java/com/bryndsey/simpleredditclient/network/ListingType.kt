package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

enum class ListingType {
    @SerializedName("t1")	COMMENT,
    @SerializedName("t2")	ACCOUNT,
    @SerializedName("t3")	LINK,
    @SerializedName("t4")	MESSAGE,
    @SerializedName("t5")	SUBREDDIT,
    @SerializedName("t6")	AWARD
}