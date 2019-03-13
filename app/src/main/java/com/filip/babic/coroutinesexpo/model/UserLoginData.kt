package com.filip.babic.coroutinesexpo.model

import com.filip.babic.coroutinesexpo.common.isEmailValid

data class UserLoginData(
    var email: String = "",
    var password: String = ""
) {

    fun isValid() = isEmailValid(email) && password.length >= 6
}