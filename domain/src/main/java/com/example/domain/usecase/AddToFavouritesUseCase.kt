package com.example.domain.usecase

import com.example.domain.contract.CourseRepository
import com.example.domain.dataclass.Course
import javax.inject.Inject

//UseCase class handles adding a course to favorites.
class AddToFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository

) {
    //allows direct call class a function.
    suspend operator fun invoke(course: Course){
        repository.addToFavorites(course)
    }
}