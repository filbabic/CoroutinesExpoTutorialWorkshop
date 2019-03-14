package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.UserData
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthenticationInteractorImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationInteractor {

    override fun registerUser(
        email: String,
        password: String,
        username: String,
        onUserAuthenticated: (Boolean, String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { authTask ->
            val isSuccessful = authTask.isComplete && authTask.isSuccessful

            if (isSuccessful) {
                val userData = firebaseAuth.currentUser

                userData?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())
                    ?.addOnCompleteListener { updateTask ->
                        onUserAuthenticated(
                            updateTask.isComplete && updateTask.isSuccessful,
                            userData.uid
                        )
                    }
                    ?: onUserAuthenticated(false, userData?.uid ?: "")
            } else {
                onUserAuthenticated(false, "")
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Deferred<Boolean> = GlobalScope.async {
        println("I'm getting the result")
        val task = Tasks.await(firebaseAuth.signInWithEmailAndPassword(email, password))

        task != null && task.user != null
    }

    override fun getUserData(): UserData {
        val data = firebaseAuth.currentUser ?: return UserData("", "", "")

        return UserData(data.uid, data.email ?: "", data.displayName ?: "")
    }

    override fun isLoggedIn(): Boolean = getUserData().isValid()
}