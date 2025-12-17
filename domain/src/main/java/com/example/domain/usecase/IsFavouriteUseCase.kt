package com.example.domain.usecase

import com.example.domain.contract.CourseRepository
import javax.inject.Inject

//Checks if a course is marked as favorites.
class IsFavouriteUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int): Boolean {
    return repository.isFavorite(courseId)
    }
}