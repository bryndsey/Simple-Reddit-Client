package com.bryndsey.simpleredditclient.data

import com.bryndsey.simpleredditclient.data.TimeConstants.MILLIS_PER_SECOND
import com.bryndsey.simpleredditclient.network.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RedditPostRepositoryTest {

    @Mock
    private lateinit var mockRedditService: RedditService

    @InjectMocks
    private lateinit var repository: RedditPostRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        // TODO: Properly mock the IO scheduler
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }

    @Test
    fun fetchRedditPosts_responseContainsPosts_convertedPostsEmitted() {
        val testTitle = "testTitle"
        val testText = "testText"
        val testScore = 1337
        val testNumComments = 12345
        val testUrl = "testUrl"
        val testId = "testId"
        val testFullname = "testFullname"
        val testIsSelf = true
        val testCreatedTimeUtcSeconds = 123456789L
        val testPostHint = "testHint"

        val testApiRedditResponse = ApiRedditResponse(
                ApiRedditResponseData(
                        listOf(
                                ApiRedditListingHolder(
                                        ListingType.LINK,
                                        ApiRedditListing(
                                                title = testTitle,
                                                text = testText,
                                                score = testScore,
                                                numComments = testNumComments,
                                                url = testUrl,
                                                id = testId,
                                                fullname = testFullname,
                                                isSelf = testIsSelf,
                                                createdTimeUtcSeconds = testCreatedTimeUtcSeconds,
                                                postHint = testPostHint,
                                                displayName = null,
                                                displayNamePrefixed = null,
                                                shortDescription = null,
                                                subscriberCount = null
                                        )
                                )
                        )
                )
        )

        whenever(mockRedditService.getSubredditPosts(any(), anyOrNull()))
                .thenReturn(Single.just(testApiRedditResponse))

        val expectedRedditPost = RedditPost(
                title = testTitle,
                text = testText,
                score = testScore,
                numComments = testNumComments,
                url = testUrl,
                id = testId,
                fullname = testFullname,
                isSelf = testIsSelf,
                createdDateMillis = testCreatedTimeUtcSeconds * MILLIS_PER_SECOND,
                postHintType = PostHintType.OTHER
        )

        repository.fetchRedditPosts("testSubredditName")
                .test()
                .assertComplete()
                .assertValue{
                    it.size == 1 && it[0] == expectedRedditPost
                }
    }
}