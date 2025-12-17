package com.example.feature_home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.dataclass.Course
import com.example.domain.contract.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Manages list course and favorites.
@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    //Backing StateFlow holding list of courses
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    //Public immutable StateFlow exposed to UI.
    val courses: StateFlow<List<Course>> = _courses


    // Load courses when ViewModel is created.
    init {
        Log.d("CourseViewModel", "ViewModel создан")
        viewModelScope.launch {
            try {
                val result = repository.getCourses()
                Log.d("CourseViewModel", "Загружено курсов: ${result.size}")
                _courses.value = result
            } catch (e: Exception) {
                Log.e("CourseViewModel", "Ошибка загрузки курсов", e)
            }
        }
    }

    //Toggle favorite state.
    fun onFavoritClick(course: Course) {
        viewModelScope.launch {
            if (repository.isFavorite(course.id)) {
                repository.removeFromFavorites(course)
            } else {
                repository.addToFavorites(course)
            }

            // перезагружаем список (чтобы обновилось поле isFavorite)
            _courses.value = repository.getCourses()
        }
    }
}
