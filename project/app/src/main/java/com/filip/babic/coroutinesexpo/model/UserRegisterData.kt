package com.filip.babic.coroutinesexpo.model

import com.filip.babic.coroutinesexpo.common.isEmailValid

data class UserRegisterData(
    var email: String = "",
    var password: String = "",
    var username: String = ""
) {

    fun isValid() = isEmailValid(email) && password.length >= 6 && username.isNotBlank()
}