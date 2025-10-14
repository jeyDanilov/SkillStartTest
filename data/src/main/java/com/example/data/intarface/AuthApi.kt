package com.example.data.intarface

import com.example.data.dataclass.UserResponse
import retrofit2.http.GET

interface AuthApi {
    @GET("auth/login.json")
    suspend fun login(): UserResponse
}