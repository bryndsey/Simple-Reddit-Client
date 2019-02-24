package com.bryndsey.simpleredditclient.data

object TimeConstants {
    const val MILLIS_PER_SECOND = 1000L
    const val SECONDS_PER_MINUTE = 60L
    const val MINUTES_PER_HOUR = 60L
    const val HOURS_PER_DAY = 24L
    const val DAYS_PER_WEEK = 7L

    const val MILLIS_PER_MINUTE = MILLIS_PER_SECOND * SECONDS_PER_MINUTE
    const val MILLIS_PER_HOUR = MILLIS_PER_MINUTE * MINUTES_PER_HOUR
    const val MILLIS_PER_DAY = MILLIS_PER_HOUR * HOURS_PER_DAY
    const val MILLIS_PER_WEEK = MILLIS_PER_DAY * DAYS_PER_WEEK
}