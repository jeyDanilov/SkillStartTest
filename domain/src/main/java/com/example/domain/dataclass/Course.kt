package com.example.domain.dataclass

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val publishDate: String,
    val price: String,
    val isFavorite: Boolean = false
)