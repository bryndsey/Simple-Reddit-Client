package com.bryndsey.simpleredditclient.network

import com.google.gson.annotations.SerializedName

data class ApiRedditListingHolder(
        @SerializedName("kind") val listingType: ListingType,
        @SerializedName("data") val data: ApiRedditListing)