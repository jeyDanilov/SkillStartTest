package com.example.domain.usecase

import com.example.domain.contract.CourseRepository
import com.example.domain.dataclass.Course
import javax.inject.Inject

//Handles retrieving favorites courses.
class GetFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): List<Course> {
        return repository.getFavorites()
    }
}