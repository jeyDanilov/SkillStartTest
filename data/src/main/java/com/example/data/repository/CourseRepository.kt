package com.example.feature_home.repository

import com.example.data.dataclass.Course
import com.example.data.intarface.CourseApi
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val api: CourseApi

) {
    suspend fun getCourses(): List<Course> {
        return api.getCourses().course

    }
}