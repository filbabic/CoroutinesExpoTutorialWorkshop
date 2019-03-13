package com.filip.babic.coroutinesexpo.ui.feed

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.common.onClick
import com.filip.babic.coroutinesexpo.model.Post
import com.filip.babic.coroutinesexpo.ui.posts.post.AddPostActivity
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.android.ext.android.inject

class FeedFragment : Fragment(), FeedContract.View {

    private val presenter by inject<FeedContract.Presenter>()
    private val adapter by lazy { FeedAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun initUi() {
        feedList.adapter = adapter
        feedList.layoutManager = LinearLayoutManager(activity)
        feedList.setHasFixedSize(true)
        addPostButton.onClick { startActivity(Intent(activity, AddPostActivity::class.java)) }
    }

    override fun showPosts(posts: List<Post>) = adapter.setData(posts)
}