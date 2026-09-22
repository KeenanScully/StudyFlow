package com.studyflow.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.studyflow.app.data.auth.AuthRepository
import com.studyflow.app.navigation.Routes
import com.studyflow.app.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel()
) {

    val authRepository = AuthRepository()

    val displayName by
    settingsViewModel.displayName.collectAsState()

    val email by
    settingsViewModel.email.collectAsState()

    val message by
    settingsViewModel.message.collectAsState()

    var editedName by remember {
        mutableStateOf("")
    }

    // Loads the Firebase user's profile information.
    LaunchedEffect(Unit) {
        settingsViewModel.loadUser()
    }

    // Copies the existing display name into the editable field.
    LaunchedEffect(displayName) {
        editedName = displayName
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Settings"
        )

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "Email: $email"
        )

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        OutlinedTextField(
            value = editedName,
            onValueChange = {
                editedName = it
            },
            label = {
                Text("Display Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {

                if (editedName.isNotBlank()) {

                    settingsViewModel
                        .updateDisplayName(
                            editedName.trim()
                        )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Save Changes")
        }

        if (message != null) {

            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = message ?: ""
            )
        }

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {

                // Signs the user out of Firebase.
                authRepository.logout()

                navController.navigate(Routes.LOGIN) {

                    popUpTo(Routes.DASHBOARD) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Logout")
        }

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Back to Dashboard")
        }
    }
}