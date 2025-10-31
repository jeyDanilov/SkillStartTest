package com.example.domain.contract

import com.example.domain.dataclass.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun toggleFavorite(courseId: Int, isLiked: Boolean)
    suspend fun getFavorites(): List<Course>

}


