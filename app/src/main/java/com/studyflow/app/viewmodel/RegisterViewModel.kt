package com.studyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyflow.app.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _registerState = MutableStateFlow<String?>(null)
    val registerState: StateFlow<String?> = _registerState

    //Creates a new Firebase account for the user.
    fun register(email: String, password: String) {

        viewModelScope.launch {

            val result = authRepository.register(email, password)

            if (result.isSuccess) {
                _registerState.value = "SUCCESS"
            }else {
                _registerState.value =
                    result.exceptionOrNull()?.message
                        ?: "Registration failed"
            }
        }
    }
}