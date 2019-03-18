package com.filip.babic.coroutinesexpo.ui.profile

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl
import kotlinx.coroutines.isActive

class ProfilePresenter(
    private val storageInteractor: StorageInteractor,
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        super.start()

        runInBackground {
            val userProfile = storageInteractor.getUserProfile(authenticationInteractor.getUserData().id)

            if (it.coroutineContext.isActive) {
                println("Rendering data")
                viewAction { render(userProfile) }
            } else {
                println("I've been cancelled")
            }
        }
    }

    override fun deletePosts(selectedPosts: List<String>) {
        runInBackground {
            storageInteractor.deletePosts(selectedPosts) {
                viewAction { removePosts(selectedPosts) }

                start() // refresh everything
            }
        }
    }
}