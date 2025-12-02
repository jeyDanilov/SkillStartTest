package com.example.domain.contract

import com.example.domain.dataclass.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun toggleFavorite(courseId: Int, isLiked: Boolean)
    suspend fun getFavorites(): List<Course>
    suspend fun addToFavorites(course: Course)

    suspend fun removeFromFavorites(course: Course)

    suspend fun isFavorite(courseId: Int): Boolean

}


