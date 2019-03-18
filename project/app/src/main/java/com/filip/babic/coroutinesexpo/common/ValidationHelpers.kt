package com.filip.babic.coroutinesexpo.common

import android.util.Patterns

fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()