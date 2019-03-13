package com.filip.babic.coroutinesexpo.ui.login

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.model.UserLoginData
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class LoginPresenter(
    private val authenticationInteractor: AuthenticationInteractor
) : BasePresenterImpl<LoginContract.View>(), LoginContract.Presenter {

    private val userLoginData = UserLoginData()

    override fun emailUpdated(email: String) {
        userLoginData.email = email
    }

    override fun passwordUpdated(password: String) {
        userLoginData.password = password
    }

    override fun submit() {
        if (userLoginData.isValid()) {
            val (email, password) = userLoginData

            authenticationInteractor.loginUser(email, password) { isSuccessful ->
                if (isSuccessful) {
                    viewAction { startHomeScreen() }
                }
            }
        }
    }
}