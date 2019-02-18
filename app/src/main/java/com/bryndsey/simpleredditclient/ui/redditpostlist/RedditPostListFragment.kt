package com.bryndsey.simpleredditclient.ui.redditpostlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bryndsey.simpleredditclient.R
import com.bryndsey.simpleredditclient.data.RedditPost
import com.bryndsey.simpleredditclient.di.ComponentHolder
import com.bryndsey.simpleredditclient.di.ViewModelFactory
import com.bryndsey.simpleredditclient.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.reddit_post_list.*
import javax.inject.Inject

class RedditPostListFragment: BaseFragment() {

    private val adapter = RedditPostListAdapter()

    @Inject lateinit var viewModelFactory: ViewModelFactory<RedditPostListViewModel>
    private lateinit var viewModel: RedditPostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentHolder.applicationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(RedditPostListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reddit_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        post_list.adapter = adapter
        // TODO: Properly handle nullability
        val disposable = viewModel.getRedditPosts(arguments!!.getString("subredditName")!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ postList -> updatePosts(postList) },
                        { Toast.makeText(context, "Error occurred fetching posts", Toast.LENGTH_SHORT).show() }
                )

        addSubscription(disposable)
    }

    private fun updatePosts(postList: List<RedditPost>) {
        adapter.setAdapterData(postList)
    }
}