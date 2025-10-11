package com.example.data.maper

import com.example.data.dataclass.UserResponse
import com.example.domain.dataclass.User


fun UserResponse.toDomain(): User {
    return User(
        id = this.id,
        name = this.name
    )
}
