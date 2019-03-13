package com.filip.babic.coroutinesexpo.ui.posts.featured

import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class FeaturedPostsPresenter(
    private val storageInteractor: StorageInteractor
) : BasePresenterImpl<FeaturedPostsContract.View>(), FeaturedPostsContract.Presenter {

    override fun start() {
        super.start()
        storageInteractor.getFeaturedPosts { posts -> viewAction { showFeaturedPosts(posts) } }
    }
}