package com.filip.babic.coroutinesexpo.ui.posts.featured

import com.filip.babic.coroutinesexpo.model.Post
import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface FeaturedPostsContract {

    interface View : BaseView {

        fun showFeaturedPosts(data: List<Post>)
    }

    interface Presenter : BasePresenter<View> {

    }
}
