package com.studyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyflow.app.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _loginState = MutableStateFlow<String?>(null)
    val loginState: StateFlow<String?> = _loginState

    //Attempt to authenticate user using Firebase.
    fun login(email: String, password: String) {

        viewModelScope.launch {

            val result = authRepository.login(email, password)

            if (result.isSuccess) {
                _loginState.value = "SUCCESS"
            } else {
                _loginState.value =
                    result.exceptionOrNull()?.message
                        ?: "Login failed"
            }
        }
    }
}