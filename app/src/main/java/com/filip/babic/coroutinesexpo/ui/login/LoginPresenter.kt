package com.filip.babic.coroutinesexpo.ui.login

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.model.UserLoginData
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

            GlobalScope.launch(context = Dispatchers.Default) {
                val isAuthenticatedDeferred = authenticationInteractor.loginUser(email, password)
                println("I'm deferring a value")

                if (isAuthenticatedDeferred.await()) {
                    println("I've compiled the value")
                    viewAction { startHomeScreen() }
                }
            }
        }
    }
}