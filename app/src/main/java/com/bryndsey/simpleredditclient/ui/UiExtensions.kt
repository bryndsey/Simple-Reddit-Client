package com.bryndsey.simpleredditclient.ui

fun Int.toDisplayString(): String {
    return if (this > 1000) {
        val numberInThousands = this / 1000F
        "%.1fK".format(numberInThousands)
    } else {
        toString()
    }
}