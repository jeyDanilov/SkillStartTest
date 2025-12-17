package com.example.feature_login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for authorization.
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserCase: LoginUserCase
) : ViewModel() {

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // Validate input fields for login.
    fun onInputChanged(email: String, password: String) {
        val isEmailValid = email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
        val isPasswordValid = password.isNotBlank()
        _isLoginEnabled.value = isEmailValid && isPasswordValid
    }

    // Perform login process.
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val result = loginUserCase(email, password)
            _loginState.value = if (result.isSuccess) {
                LoginState.Success(result.getOrNull()!!)
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}
