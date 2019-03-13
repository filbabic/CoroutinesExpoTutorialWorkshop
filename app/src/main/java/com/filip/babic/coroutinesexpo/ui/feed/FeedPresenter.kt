package com.filip.babic.coroutinesexpo.ui.feed

import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class FeedPresenter(
    private val storageInteractor: StorageInteractor
) : BasePresenterImpl<FeedContract.View>(), FeedContract.Presenter {

    override fun start() {
        super.start()
        storageInteractor.getPosts { posts -> viewAction { showPosts(posts) } }
    }
}