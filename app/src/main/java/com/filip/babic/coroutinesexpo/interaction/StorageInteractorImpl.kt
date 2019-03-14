package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.*
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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

    override suspend fun getUserProfile(userId: String): UserProfile {
        val userDeferred = GlobalScope.async {
            println("Getting the user")
            val document = Tasks.await(
                firestore.collection(COLLECTION_USERS)
                    .whereEqualTo("id", userId)
                    .get()
            )

            document?.documents?.first()?.toObject(User::class.java) ?: User()
        }

        val postsDeferred = GlobalScope.async {
            println("Getting the posts for my user")
            val snapshot = Tasks.await(
                firestore.collection(COLLECTION_POSTS)
                    .whereEqualTo("authorId", userId)
                    .get()
            )

            snapshot.toObjects(Post::class.java).filterNotNull()
        }

        val featuredDeferred = GlobalScope.async {
            println("Getting the featured posts for my user")
            val featuredSnapshot = Tasks.await(
                firestore.collection(COLLECTION_FEATURED)
                    .whereEqualTo("authorId", userId)
                    .get()
            )

            featuredSnapshot.toObjects(Post::class.java).filterNotNull()
        }

        return UserProfile(userDeferred.await(), postsDeferred.await(), featuredDeferred.await())
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

    override fun deletePosts(posts: List<String>, onPostsDeleted: () -> Unit) {
        val rootPosts = firestore.collection(COLLECTION_FEATURED)

        posts.forEach {
            rootPosts.document(it).delete().addOnSuccessListener {
                println("Deleted")
            }
        }

        onPostsDeleted()
    }

    override fun deletePost(post: String) {
        firestore.collection(COLLECTION_FEATURED).document(post).delete().addOnSuccessListener {
            println("Deleted")
        }
    }
}