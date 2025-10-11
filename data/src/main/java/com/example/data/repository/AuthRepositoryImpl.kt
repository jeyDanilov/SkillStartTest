package com.example.data.repository

import android.R.attr.id
import android.R.attr.name
import com.example.data.dataclass.LoginRequest
import com.example.data.dataclass.UserResponse
import com.example.data.intarface.AuthApi
import com.example.domain.dataclass.User
import com.example.domain.`interface`.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User> {
        return try {
            val response = api.login(LoginRequest(email, password))
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun UserResponse.toDomain(): User = User(id, name)
}