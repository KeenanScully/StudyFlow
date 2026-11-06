package com.studyflow.app.ui.screens

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.studyflow.app.utils.LanguageManager
import com.studyflow.app.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel()
) {

    val authRepository = AuthRepository()

    val displayName by settingsViewModel.displayName.collectAsState()
    val email by settingsViewModel.email.collectAsState()
    val message by settingsViewModel.message.collectAsState()

    var editedName by remember { mutableStateOf("") }

    var languageExpanded by remember { mutableStateOf(false) }

    val currentLanguage =
        AppCompatDelegate.getApplicationLocales()
            .get(0)?.language
            ?: "en"

    val selectedLanguage = when (currentLanguage) {
        "af" -> "Afrikaans"
        else -> "English"
    }

    LaunchedEffect(Unit) {
        settingsViewModel.loadUser()
    }

    LaunchedEffect(displayName) {
        editedName = displayName
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Email: $email")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = editedName,
            onValueChange = { editedName = it },
            label = { Text("Display Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (editedName.isNotBlank()) {
                    settingsViewModel.updateDisplayName(
                        editedName.trim()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }

        if (message != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message ?: "")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleMedium
        )

        var selectedLanguage by remember {
            mutableStateOf("English")
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Select Language")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    selectedLanguage = "English"
                    LanguageManager.setLanguage("en")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("English")
            }

            Button(
                onClick = {
                    selectedLanguage = "Afrikaans"
                    LanguageManager.setLanguage("af")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Afrikaans")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
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

        Spacer(modifier = Modifier.height(10.dp))

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