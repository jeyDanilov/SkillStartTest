package com.example.feature_home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dataclass.Course
import com.example.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses

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
}