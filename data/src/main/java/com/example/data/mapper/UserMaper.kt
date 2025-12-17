package com.example.data.mapper

import com.example.data.dataclass.UserResponse
import com.example.domain.dataclass.User

// Convert UserResponse to User.
fun UserResponse.toDomain(): User {
    return User(
        id = this.id.toString(),
        name = this.name,
    )
}
