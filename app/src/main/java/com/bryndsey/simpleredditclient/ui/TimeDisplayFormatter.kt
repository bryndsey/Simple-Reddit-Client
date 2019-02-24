package com.bryndsey.simpleredditclient.ui

import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_DAY
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_HOUR
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_MINUTE
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_WEEK

object TimeDisplayFormatter {

    fun getStringForTimeSince(utcTimeMillis: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val timeDifference = currentTimeMillis - utcTimeMillis
        return getStringForTimeLength(timeDifference)
    }

    fun getStringForTimeLength(lengthInMillis: Long): String {
        return when {
            lengthInMillis < 0 -> throw IllegalArgumentException("Negative post_time length is not allowed")
            lengthInMillis < MILLIS_PER_HOUR -> (lengthInMillis / MILLIS_PER_MINUTE).toString() + "m"
            lengthInMillis < MILLIS_PER_DAY -> (lengthInMillis / MILLIS_PER_HOUR).toString() + "h"
            lengthInMillis < MILLIS_PER_WEEK -> (lengthInMillis / MILLIS_PER_DAY).toString() + "d"
            // TODO: Add conversions for months and years
            else -> (lengthInMillis / MILLIS_PER_WEEK).toString() + "w"
        }
    }
}