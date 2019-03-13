package com.filip.babic.coroutinesexpo.interaction

import com.filip.babic.coroutinesexpo.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

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

    override fun loginUser(email: String, password: String, onUserAuthenticated: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onUserAuthenticated(it.isComplete && it.isSuccessful)
        }
    }

    override fun getUserData(): UserData {
        val data = firebaseAuth.currentUser ?: return UserData("", "", "")

        return UserData(data.uid, data.email ?: "", data.displayName ?: "")
    }

    override fun isLoggedIn(): Boolean = getUserData().isValid()
}