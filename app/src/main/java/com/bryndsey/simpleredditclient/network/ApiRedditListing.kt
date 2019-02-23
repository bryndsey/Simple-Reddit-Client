package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class ApiRedditListing(
        @SerializedName("title") val title: String?,
        @SerializedName("selftext") val text: String?,
        @SerializedName("score") val score: Int?,
        @SerializedName("num_comments") val numComments: Int?,
        @SerializedName("url") val url: String?,
        @SerializedName("id") val id: String,
        @SerializedName("is_self") val isSelf: Boolean,
        @SerializedName("created_utc") val createdTimeUtcSeconds: Long?,

        @SerializedName("display_name") val displayName: String?,
        @SerializedName("display_name_prefixed") val displayNamePrefixed: String?,
        @SerializedName("public_description") val shortDescription: String?,
        @SerializedName("subscribers") val subscriberCount: Int?
)