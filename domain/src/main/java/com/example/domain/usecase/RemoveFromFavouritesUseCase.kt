package com.example.domain.usecase

import com.example.domain.contract.CourseRepository
import com.example.domain.dataclass.Course
import javax.inject.Inject

class RemoveFromFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(course: Course) {
        repository.removeFromFavorites(course)
    }
}