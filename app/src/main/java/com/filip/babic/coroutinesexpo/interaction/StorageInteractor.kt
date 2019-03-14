package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.Post
import com.filip.babic.coroutinesexpo.model.PostData
import com.filip.babic.coroutinesexpo.model.UserItem
import com.filip.babic.coroutinesexpo.model.UserProfile

interface StorageInteractor {

    fun saveUser(email: String, username: String, id: String)

    fun addPost(post: PostData, authorId: String, onPostAdded: () -> Unit)

    fun addFeaturedPost(post: PostData, authorId: String, onPostAdded: () -> Unit)

    fun getUserProfile(userId: String, onUserProfileLoaded: (UserProfile) -> Unit)

    fun getUsers(onUsersLoaded: (List<UserItem>) -> Unit)

    fun getPosts(onPostsLoaded: (List<Post>) -> Unit)

    fun getFeaturedPosts(onPostsLoaded: (List<Post>) -> Unit)

    fun deletePosts(posts: List<String>, onPostsDeleted: () -> Unit)

    fun deletePost(post: String)
}