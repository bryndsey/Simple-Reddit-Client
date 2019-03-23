package com.bryndsey.simpleredditclient.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ApiRedditResponseSerializer : JsonDeserializer<ApiRedditResponse?> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ApiRedditResponse? {
        if (json == null) return null

        if (json.isJsonObject) {
            val jObject = json.asJsonObject
            val jDataElement = jObject.get("data")
            val responseData = context?.deserialize<ApiRedditResponseData>(jDataElement, ApiRedditResponseData::class.java)
            // TODO: Handle null case better
            return ApiRedditResponse(responseData!!)
        }

        return null
    }

}