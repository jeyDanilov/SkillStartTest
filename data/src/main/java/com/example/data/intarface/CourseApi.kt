package com.example.data.intarface

import com.example.data.dataclass.CourseResponse
import retrofit2.http.GET

interface CourseApi {
    @GET("courses.json")
    suspend fun getCourses(): CourseResponse
}
