package com.bryndsey.simpleredditclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bryndsey.simpleredditclient.network.RedditPost
import com.bryndsey.simpleredditclient.network.RedditPostData
import com.bryndsey.simpleredditclient.network.RedditService
import com.bryndsey.simpleredditclient.ui.RedditPostAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RedditPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RedditPostAdapter()
        post_list.adapter = adapter
        post_list.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val redditService = retrofit.create(RedditService::class.java)

        val postObservable: Observable<List<RedditPost>> = Observable.create { subscriber ->
            val callResponse = redditService.getSubredditPosts()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val posts = response.body()!!.data.posts
                subscriber.onNext(posts)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

        postObservable
                .retry()
                .flatMap { list -> Observable.fromIterable(list) }
                .map { redditPost -> redditPost.data }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ postList -> updatePosts(postList)},
                        { error -> Toast.makeText(this, "Error occurred fetching posts", Toast.LENGTH_SHORT).show() }
        )

    }
        private fun updatePosts(postList: List<RedditPostData>) {
            adapter.postList = postList
            adapter.notifyDataSetChanged()
        }
}
