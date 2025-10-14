package com.example.domain.usecase

import com.example.domain.contract.AuthRepository
import com.example.domain.dataclass.User
import javax.inject.Inject


    class LoginUserCase @Inject constructor(
        private val authRepository: AuthRepository
    ) {
        suspend operator fun invoke(email: String, password: String): Result<User> {
            return authRepository.login(email, password)
        }
    }

