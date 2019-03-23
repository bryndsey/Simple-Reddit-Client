package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.network.ApiRedditResponse
import com.bryndsey.simpleredditclient.network.RedditService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

private const val ORIGINAL_POST_INDEX_IN_RESPONSE = 0
private const val COMMENTS_INDEX_IN_RESPONSE = 1

class FetchCommentUseCase(private val redditService: RedditService) {

    fun fetchComments(postId: String) : Single<List<Comment>> {
        return redditService.getPostComments(postId)
                .subscribeOn(Schedulers.io())
                .map {
                    if (it.size > COMMENTS_INDEX_IN_RESPONSE) {
                        convertResponseToComments(it[COMMENTS_INDEX_IN_RESPONSE])
                    } else {
                        emptyList()
                    }
                }
    }

    private fun convertResponseToComments(response: ApiRedditResponse?) : List<Comment> {
        if (response == null) return emptyList()

        val listings = response.data.posts

        return listings.map { listingHolder ->
            val listing = listingHolder.data

            // Convert comment replies recursively
            val replies = convertResponseToComments(listing.replies)
            
            Comment(
                    text = listing.commentText,
                    score = listing.score,
                    replies = replies
            )
        }
    }
}