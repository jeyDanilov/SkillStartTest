package com.example.data.maper

import com.example.data.dataclass.UserResponse
import com.example.domain.dataclass.User

// Преобразование UserResponse в User
fun UserResponse.toDomain(): User {
    return User(
        id = this.id.toString(),
        name = this.name,
    )
}
