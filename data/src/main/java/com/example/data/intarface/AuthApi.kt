package com.example.data.intarface

import com.example.data.dataclass.LoginRequest
import com.example.data.dataclass.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): UserResponse
}