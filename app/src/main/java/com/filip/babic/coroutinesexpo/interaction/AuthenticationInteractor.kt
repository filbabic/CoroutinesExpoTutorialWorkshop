package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.UserData

interface AuthenticationInteractor {

    fun registerUser(email: String, password: String, username: String, onUserAuthenticated: (Boolean, String) -> Unit)

    fun loginUser(email: String, password: String, onUserAuthenticated: (Boolean) -> Unit)

    fun getUserData(): UserData

    fun isLoggedIn(): Boolean
}