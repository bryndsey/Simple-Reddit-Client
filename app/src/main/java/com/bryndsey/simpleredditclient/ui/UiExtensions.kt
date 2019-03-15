package com.bryndsey.simpleredditclient.ui

fun Int.toDisplayString(): String {
    // FIXME: This should use >=
    return if (this > 1000) {
        val numberInThousands = this / 1000F
        "%.1fK".format(numberInThousands)
    } else {
        toString()
    }
}