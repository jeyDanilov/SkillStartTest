package com.example.data.dataclass

// Response course.
data class CourseResponse(
    val courses: List<CourseDto>
)

// Model course.
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