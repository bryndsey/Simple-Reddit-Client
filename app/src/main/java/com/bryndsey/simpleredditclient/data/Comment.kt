package com.bryndsey.simpleredditclient.data

data class Comment(
        val text: String?,
        val score: Int?,
        val createdDateMillis: Long?,
        val replies: List<Comment> = emptyList()
)