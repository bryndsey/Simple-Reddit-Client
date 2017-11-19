package com.bryndsey.simpleredditclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bryndsey.simpleredditclient.network.RedditPost
import com.bryndsey.simpleredditclient.network.RedditService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        postObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe{ postList -> Log.d("BRYAN", postList.toString())}
    }
}
