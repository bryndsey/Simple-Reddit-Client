package com.bryndsey.simpleredditclient.data

data class Comment(
        val text: String,
        val score: Int,
        val childComments: List<Comment> = emptyList()
)