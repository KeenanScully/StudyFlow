package com.studyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyflow.app.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _displayName =
        MutableStateFlow("")

    val displayName: StateFlow<String> = _displayName

    private val _email =
        MutableStateFlow("")

    val email: StateFlow<String> = _email

    private val _message =
        MutableStateFlow<String?>(null)

    val message: StateFlow<String?> = _message

    // Loads information about the currently signed-in user.
    fun loadUser() {

        val user = authRepository.getCurrentUser()

        _displayName.value =
            user?.displayName ?: ""

        _email.value =
            user?.email ?: ""
    }

    // Saves the new display name through Firebase.
    fun updateDisplayName(name: String) {

        viewModelScope.launch {

            val result =
                authRepository.updateDisplayName(name)

            if (result.isSuccess) {

                _displayName.value = name
                _message.value =
                    "Profile updated successfully."

            } else {

                _message.value =
                    result.exceptionOrNull()?.message
                        ?: "Unable to update profile."
            }
        }
    }
}