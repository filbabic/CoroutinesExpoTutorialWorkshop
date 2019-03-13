package com.filip.babic.coroutinesexpo.ui.register

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.model.UserRegisterData
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class RegisterPresenter(
    private val authenticationInteractor: AuthenticationInteractor,
    private val interactor: StorageInteractor
) : BasePresenterImpl<RegisterContract.View>(), RegisterContract.Presenter {

    private val userData = UserRegisterData()

    override fun emailUpdated(email: String) {
        userData.email = email
    }

    override fun passwordUpdated(password: String) {
        userData.password = password

    }

    override fun usernameUpdated(username: String) {
        userData.username = username
    }

    override fun submit() {
        if (userData.isValid()) {
            val (email, password, username) = userData

            authenticationInteractor.registerUser(email, password, username) { isSuccessful, id ->
                if (isSuccessful) {
                    saveUserProfile(email, username, id)
                    viewAction { startHomeScreen() }
                } else {
                    // show error
                }
            }
        }
    }

    private fun saveUserProfile(email: String, username: String, id: String) {
        interactor.saveUser(email, username, id)
    }
}