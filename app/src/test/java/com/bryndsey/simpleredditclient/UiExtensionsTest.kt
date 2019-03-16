package com.bryndsey.simpleredditclient

import com.bryndsey.simpleredditclient.ui.toDisplayString
import junit.framework.Assert.assertEquals
import org.junit.Test

class UiExtensionsTest {

    @Test
    fun numberLessThan1000_displayStringIsSameAsDefaultToString() {
        val inputNumber = 123

        assertEquals(inputNumber.toString(), inputNumber.toDisplayString())
    }

    @Test
    fun numberGreaterThan1000_displayStringIsShowsFormattedNumber() {
        val inputNumber = 1234

        assertEquals("1.2K", inputNumber.toDisplayString())
    }

    @Test
    fun numberEquals1000_displayStringIsShowsFormattedNumber() {
        val inputNumber = 1000

        assertEquals("1.0K", inputNumber.toDisplayString())
    }
}