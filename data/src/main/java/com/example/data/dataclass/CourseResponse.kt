package com.example.data.dataclass

// Ответ с курсами
data class CourseResponse(
    val courses: List<Course>
)

// Модель курса
data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean = false,
    val publishDate: String
)