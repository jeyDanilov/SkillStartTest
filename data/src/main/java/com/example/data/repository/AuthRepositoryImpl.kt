package com.example.data.repository


import com.example.data.dataclass.UserResponse
import com.example.data.intarface.AuthApi
import com.example.domain.dataclass.User
import com.example.domain.contract.AuthRepository
import javax.inject.Inject

// Implementation AuthRepository.
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    // Authorization User.
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = api.login()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Mapper UserResponse in the User.
    private fun UserResponse.toDomain(): User = User(
        id = id.toString(),
        name = name,
    )
}