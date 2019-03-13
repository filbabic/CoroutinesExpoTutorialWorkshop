package com.filip.babic.coroutinesexpo.ui.profile

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class ProfilePresenter(
    private val storageInteractor: StorageInteractor,
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<ProfileContract.View>(), ProfileContract.Presenter {

    override fun start() {
        super.start()
        storageInteractor.getUserProfile(authenticationInteractor.getUserData().id) {
            viewAction { render(it) }
        }
    }
}