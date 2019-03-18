package com.filip.babic.coroutinesexpo.ui.feed

import com.filip.babic.coroutinesexpo.model.Post
import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface FeedContract {

    interface View : BaseView {

        fun showPosts(posts: List<Post>)
    }

    interface Presenter : BasePresenter<View>
}