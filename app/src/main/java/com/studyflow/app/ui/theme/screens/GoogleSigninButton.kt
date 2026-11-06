package com.studyflow.app.ui.theme.screens

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.studyflow.app.data.auth.GoogleAuthRepository
import com.studyflow.app.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun GoogleSignInButton(
    navController: NavController
) {

    val context = LocalContext.current
    val activity = context as? Activity

    val scope = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    val googleAuthRepository = remember {
        GoogleAuthRepository()
    }

    Button(
        onClick = {

            if (activity == null) {

                errorMessage = "Unable to access the Android activity."

                return@Button
            }

            scope.launch {

                isLoading = true
                errorMessage = null

                val result =
                    googleAuthRepository.signIn(activity)

                isLoading = false

                result.fold(

                    onSuccess = {

                        // Opens the dashboard after successful authentication.
                        navController.navigate(Routes.DASHBOARD) {

                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                        }
                    },

                    onFailure = { error ->

                        errorMessage =
                            error.message ?: "Google sign-in failed."
                    }
                )
            }
        },
        enabled = !isLoading,
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = if (isLoading)
                "Signing in..."
            else
                "Continue with Google"
        )
    }

    if (errorMessage != null) {

        Text(
            text = errorMessage ?: ""
        )
    }
}