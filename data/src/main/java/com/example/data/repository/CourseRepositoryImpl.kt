package com.example.data.repository

import com.example.data.intarface.CourseApi
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.room.FavoriteCourseDao
import com.example.data.room.FavoriteCourseEntity
import com.example.domain.contract.CourseRepository
import com.example.domain.dataclass.Course
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val dao: FavoriteCourseDao
) : CourseRepository {

    //Fetch courses from API and mark favorites based on local database.
    override suspend fun getCourses(): List<Course> {
        val apiCourses = api.getCourses().courses
        val favorites = dao.getFavorites().map { it.id }.toSet()

        return apiCourses.map { dto ->
            //Convert DTO to Domain and set isFavorite if course exists in local favorites.
            dto.toDomain().copy(isFavorite = dto.id in favorites)
        }
    }

    //Toggle favorite status for a course.(add,remove from local database)
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

    override suspend fun addToFavorites(course: Course) {
        return dao.add(course.toEntity())
    }

    override suspend fun removeFromFavorites(course: Course) {
        return dao.remove(course.toEntity())
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return dao.isFavorite(courseId)
    }
}
