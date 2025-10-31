package com.example.data.repository

import com.example.data.intarface.CourseApi
import com.example.data.mapper.toDomain
import com.example.data.room.FavoriteCourseDao
import com.example.data.room.FavoriteCourseEntity
import com.example.domain.contract.CourseRepository
import com.example.domain.dataclass.Course
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val dao: FavoriteCourseDao
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        val apiCourses = api.getCourses().courses
        val favorites = dao.getFavorites().map { it.id }.toSet()

        return apiCourses.map { dto ->
            dto.toDomain().copy(isFavorite = dto.id in favorites)
        }
    }

    override suspend fun toggleFavorite(courseId: Int, isLiked: Boolean) {
        val apiCourses = api.getCourses().courses
        val dto = apiCourses.find { it.id == courseId }

        dto?.let {
            val entity = FavoriteCourseEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                publishDate = it.publishDate
            )

            if (isLiked) dao.add(entity)
            else dao.remove(entity)
        }
    }

    override suspend fun getFavorites(): List<Course> {
        return dao.getFavorites().map { it.toDomain() }
    }
}
