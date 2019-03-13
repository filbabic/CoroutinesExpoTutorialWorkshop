package com.filip.babic.coroutinesexpo.ui.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.model.UserProfile
import com.filip.babic.coroutinesexpo.ui.feed.FeedAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(), ProfileContract.View {

    private val presenter by inject<ProfileContract.Presenter>()
    private val adapter by lazy { FeedAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        presenter.setView(this)
        presenter.start()
    }

    private fun initUi() {
        featuredPosts.adapter = adapter
        featuredPosts.layoutManager = LinearLayoutManager(activity)
        featuredPosts.setHasFixedSize(true)
    }

    override fun render(userProfile: UserProfile) = with(userProfile) {
        email.text = user.email
        username.text = user.username
        numberOfPosts.text = resources.getString(R.string.number_of_posts, userPosts.size + featuredPosts.size)
        adapter.setData(featuredPosts)
    }
}