package com.filip.babic.coroutinesexpo.model

data class UserProfile(
    val user: User = User(),
    val userPosts: List<Post> = emptyList(),
    val featuredPosts: List<Post> = emptyList()
)