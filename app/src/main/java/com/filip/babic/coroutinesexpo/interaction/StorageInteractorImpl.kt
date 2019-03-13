package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.*
import com.google.firebase.firestore.FirebaseFirestore

class StorageInteractorImpl(
    private val firestore: FirebaseFirestore
) : StorageInteractor {

    companion object {
        private const val COLLECTION_USERS = "users"
        private const val COLLECTION_POSTS = "posts"
        private const val COLLECTION_FEATURED = "featured"
    }

    override fun saveUser(email: String, username: String, id: String) {
        val userData = UserData(id, email, username)

        firestore.collection(COLLECTION_USERS)
            .document(id)
            .set(userData)
    }

    override fun addPost(post: PostData, authorId: String, onPostAdded: () -> Unit) {
        val newPostItem = firestore.collection(COLLECTION_POSTS).document()
        val newPost = Post(newPostItem.id, post.title, post.description, System.currentTimeMillis(), authorId)

        newPostItem.set(newPost).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                onPostAdded()
            }
        }
    }

    override fun addFeaturedPost(post: PostData, authorId: String, onPostAdded: () -> Unit) {
        val newPostItem = firestore.collection(COLLECTION_FEATURED).document()
        val newPost = Post(newPostItem.id, post.title, post.description, System.currentTimeMillis(), authorId)

        newPostItem.set(newPost).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                onPostAdded()
            }
        }
    }

    override fun getUserProfile(userId: String, onUserProfileLoaded: (UserProfile) -> Unit) {
        firestore.collection(COLLECTION_USERS)
            .whereEqualTo("id", userId)
            .get()
            .addOnSuccessListener { userSnapshot ->
                val document = userSnapshot.documents.firstOrNull()

                val user = document?.toObject(User::class.java) ?: return@addOnSuccessListener

                firestore.collection(COLLECTION_POSTS)
                    .whereEqualTo("authorId", userId)
                    .get()
                    .addOnSuccessListener { postsSnapshot ->
                        val posts = postsSnapshot.toObjects(Post::class.java).filterNotNull()

                        firestore.collection(COLLECTION_FEATURED)
                            .whereEqualTo("authorId", userId)
                            .get()
                            .addOnSuccessListener { featuredSnapshot ->
                                val featuredPosts = featuredSnapshot.toObjects(Post::class.java).filterNotNull()

                                onUserProfileLoaded(UserProfile(user, posts, featuredPosts))
                            }
                    }
            }
    }

    override fun getUsers(onUsersLoaded: (List<UserItem>) -> Unit) {
        firestore.collection(COLLECTION_USERS)
            .get()
            .addOnSuccessListener { snapshot ->
                val data = snapshot.documents
                    .mapNotNull { document -> document.toObject(User::class.java) }
                    .map { UserItem(it, 0) }

                onUsersLoaded(data)
            }
    }

    override fun getPosts(onPostsLoaded: (List<Post>) -> Unit) {
        firestore.collection(COLLECTION_POSTS)
            .get()
            .addOnSuccessListener { snapshot ->
                val data = snapshot.documents
                    .mapNotNull { document -> document.toObject(Post::class.java) }

                onPostsLoaded(data)
            }
    }

    override fun getFeaturedPosts(onPostsLoaded: (List<Post>) -> Unit) {
        firestore.collection(COLLECTION_FEATURED)
            .get()
            .addOnSuccessListener { snapshot ->
                val data = snapshot.documents
                    .mapNotNull { document -> document.toObject(Post::class.java) }

                onPostsLoaded(data)
            }
    }
}