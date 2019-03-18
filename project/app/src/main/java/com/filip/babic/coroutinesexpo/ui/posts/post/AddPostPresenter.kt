package com.filip.babic.coroutinesexpo.ui.posts.post

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.model.PostData
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class AddPostPresenter(
    private val storageInteractor: StorageInteractor,
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<AddPostContract.View>(), AddPostContract.Presenter {

    private val post = PostData()
    private var shouldFeaturePost: Boolean = false

    override fun postDescriptionChanged(description: String) {
        post.description = description
    }

    override fun postTitleChanged(title: String) {
        post.title = title
    }

    override fun onFeaturePostChanged(isChecked: Boolean) {
        this.shouldFeaturePost = isChecked
    }

    override fun submit() {
        if (shouldFeaturePost) {
            storageInteractor.addFeaturedPost(post, authenticationInteractor.getUserData().id) { viewAction { onPostAdded() } }
        } else {
            storageInteractor.addPost(post, authenticationInteractor.getUserData().id) { viewAction { onPostAdded() } }
        }
    }
}