package com.filip.babic.coroutinesexpo.ui.profile

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProfilePresenter(
    private val storageInteractor: StorageInteractor,
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<ProfileContract.View>(), ProfileContract.Presenter, CoroutineScope {

    private var parentJob = Job()

    override fun start() {
        super.start()
        parentJob = Job()

        launch {
            val userProfile = storageInteractor.getUserProfile(authenticationInteractor.getUserData().id)

            if (this.coroutineContext.isActive) {
                println("Rendering data")
                viewAction { render(userProfile) }
            } else {
                println("I've been cancelled")
            }
        }
    }

    override fun stop() {
        super.stop()
        parentJob.cancel()
    }

    override fun deletePosts(selectedPosts: List<String>) {
        storageInteractor.deletePosts(selectedPosts) {
            viewAction { removePosts(selectedPosts) }

            start() // refresh everything
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + parentJob
}