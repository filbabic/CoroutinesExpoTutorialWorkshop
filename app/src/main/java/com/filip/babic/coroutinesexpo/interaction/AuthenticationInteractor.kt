package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.UserData
import kotlinx.coroutines.Deferred

interface AuthenticationInteractor {

    fun registerUser(email: String, password: String, username: String, onUserAuthenticated: (Boolean, String) -> Unit)

    suspend fun loginUser(email: String, password: String): Deferred<Boolean>

    fun getUserData(): UserData

    fun isLoggedIn(): Boolean
}