package com.studyflow.app.data.auth

import android.app.Activity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class GoogleAuthRepository {

    private val auth = FirebaseAuth.getInstance()

    // Signs a user into Firebase using their Google account.
    suspend fun signIn(activity: Activity): Result<String> {

        return try {

            // Creates the Credential Manager instance.
            val credentialManager =
                CredentialManager.create(activity)

            // Configures the Google account selection request.
            val googleIdOption =
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        activity.getString(
                            com.studyflow.app.R.string.default_web_client_id
                        )
                    )
                    .build()

            // Builds the Google authentication request.
            val request =
                GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

            // Displays the Google account selection interface.
            val result = credentialManager.getCredential(
                context = activity,
                request = request
            )

            val credential = result.credential

            // Checks that Google returned the expected credential.
            if (
                credential is CustomCredential &&
                credential.type ==
                GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {

                val googleCredential =
                    GoogleIdTokenCredential.createFrom(
                        credential.data
                    )

                // Converts the Google token into a Firebase credential.
                val firebaseCredential =
                    GoogleAuthProvider.getCredential(
                        googleCredential.idToken,
                        null
                    )

                // Authenticates the user with Firebase.
                val firebaseResult =
                    auth.signInWithCredential(
                        firebaseCredential
                    ).await()

                val uid = firebaseResult.user?.uid
                    ?: throw Exception(
                        "Firebase authentication failed."
                    )

                Result.success(uid)

            } else {

                Result.failure(
                    Exception("Invalid Google credential.")
                )
            }

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}