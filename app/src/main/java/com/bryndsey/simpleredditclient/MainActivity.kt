package com.bryndsey.simpleredditclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.network.RedditPostData
import com.bryndsey.simpleredditclient.network.RedditService
import com.bryndsey.simpleredditclient.ui.RedditPostAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RedditPostAdapter

    @Inject
    lateinit var redditService: RedditService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ComponentHolder.applicationComponent.inject(this)

        adapter = RedditPostAdapter()
        post_list.adapter = adapter
        post_list.layoutManager = LinearLayoutManager(this)

        redditService.getSubredditPosts()
                .retry()
                .map{response -> response.data.posts }
                .flatMapObservable { list -> Observable.fromIterable(list) }
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
