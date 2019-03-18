package com.filip.babic.coroutinesexpo.ui.welcome

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class WelcomePresenter(
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<WelcomeContract.View>(), WelcomeContract.Presenter {

    override fun start() {
        super.start()

        if (authenticationInteractor.isLoggedIn()) {
            viewAction { startHomeScreen() }
        }
    }
}