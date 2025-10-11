package com.example.feature_login.viewmodel

import com.example.domain.dataclass.User

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()

}