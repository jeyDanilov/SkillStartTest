package com.example.domain.`class`
import com.example.domain.dataclass.User
import com.example.domain.`interface`.AuthRepository
import javax.inject.Inject


class LoginUserCase  @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}
