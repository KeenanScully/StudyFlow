package com.studyflow.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.studyflow.app.navigation.Routes

@Composable
fun DashboardScreen(navController: NavController) {

    val currentUser =
        FirebaseAuth.getInstance().currentUser

    val username =
        currentUser?.displayName
            ?: currentUser?.email
            ?: "Student"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome, $username"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "StudyFlow Dashboard"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                navController.navigate(Routes.MODULES)
            }
        ) {
            Text("Modules")
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = {
                navController.navigate(Routes.SETTINGS)
            }
        ) {
            Text("Settings")
        }
    }
}