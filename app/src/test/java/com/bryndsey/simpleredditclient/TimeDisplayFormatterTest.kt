package com.bryndsey.simpleredditclient

import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_DAY
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_HOUR
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_MINUTE
import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_WEEK
import com.bryndsey.simpleredditclient.ui.TimeDisplayFormatter
import org.junit.Assert
import org.junit.Test

class TimeDisplayFormatterTest{

    @Test
    fun displaysMinutesCorrectly() {
        Assert.assertEquals("1m", TimeDisplayFormatter.getStringForTimeLength( MILLIS_PER_MINUTE + 100))
    }

    @Test
    fun displaysHoursCorrectly() {
        Assert.assertEquals("1h", TimeDisplayFormatter.getStringForTimeLength(MILLIS_PER_HOUR + 100))
    }

    @Test
    fun displaysDaysCorrectly() {
        Assert.assertEquals("1d", TimeDisplayFormatter.getStringForTimeLength(MILLIS_PER_DAY + 100))
    }

    @Test
    fun displaysWeeksCorrectly() {
        Assert.assertEquals("1w", TimeDisplayFormatter.getStringForTimeLength(MILLIS_PER_WEEK + 100))
    }
}