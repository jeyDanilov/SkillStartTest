package com.example.data.repository

import android.util.Log
import com.example.data.dataclass.Course
import com.example.data.intarface.CourseApi
import com.example.data.room.FavoriteCourseDao
import com.example.data.room.FavoriteCourseEntity
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val api: CourseApi,
    private val favoriteDao: FavoriteCourseDao // ← DAO для избранного
) {

    // ✅ Получение курсов с проставленным флагом hasLike
    suspend fun getCourses(): List<Course> {
        Log.d("CourseRepository", "Requesting courses from API")
        val allCourses = api.getCourses().courses
        val favorites = favoriteDao.getAll().map { it.courseId }.toSet()
        return allCourses.map { it.copy(hasLike = it.id in favorites) }
    }

    // ✅ Добавление или удаление из избранного
    suspend fun toggleFavorite(courseId: Int, isLiked: Boolean) {
        if (isLiked) {
            favoriteDao.add(FavoriteCourseEntity(courseId))
        } else {
            favoriteDao.remove(FavoriteCourseEntity(courseId))
        }
    }

    // ✅ Получение только избранных курсов
    suspend fun getFavorites(): List<Course> {
        return getCourses().filter { it.hasLike }
    }
}