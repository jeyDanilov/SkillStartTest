package com.example.feature_login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.`class`.LoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserCase: LoginUserCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


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
