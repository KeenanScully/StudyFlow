package com.studyflow.app.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    //Creates a new Firebase account using the supplied email and password
    suspend fun register(
        email: String,
        password: String
    ): Result<String> {

        return try {

            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user?.uid ?: "")

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //Signs an existing use into Firebase.
    suspend fun login(
        email: String,
        password: String
    ): Result<String> {

        return try {

            val result = auth
                .signInWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user?.uid ?: "")

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    // Updates the currently signed-in user's display name.
    suspend fun updateDisplayName(
        name: String
    ): Result<Unit> {

        return try {

            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("No user is currently signed in.")
                )

            val profileUpdates =
                com.google.firebase.auth.userProfileChangeRequest {
                    displayName = name
                }

            user.updateProfile(profileUpdates).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    //Sign current user out of Firebase.
    fun logout() {
        auth.signOut()
    }

    //Returns the current authenticated Firebase user, if one exists.
    fun getCurrentUser() = auth.currentUser
}