package com.example.domain.contract

import com.example.domain.dataclass.User

// Репозиторий авторизации
interface AuthRepository{
    suspend fun login(email: String, password: String): Result<User>
}