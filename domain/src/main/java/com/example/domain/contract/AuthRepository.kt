package com.example.domain.`interface`

import com.example.domain.dataclass.User

interface AuthRepository{
    suspend fun login(email: String, password: String): Result<User>
}