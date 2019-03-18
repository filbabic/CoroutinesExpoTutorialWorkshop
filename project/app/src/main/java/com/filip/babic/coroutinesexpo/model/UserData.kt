package com.filip.babic.coroutinesexpo.model

data class UserData(
    val id: String,
    val email: String,
    val username: String
) {
    fun isValid(): Boolean = listOf(id, email, username).all { it.isNotBlank() }
}