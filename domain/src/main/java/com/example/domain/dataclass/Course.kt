package com.example.domain.dataclass

//Data class for course.
data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val publishDate: String,
    val imageRes: Int? = null,
    val price: String,
    val isFavorite: Boolean = false
)