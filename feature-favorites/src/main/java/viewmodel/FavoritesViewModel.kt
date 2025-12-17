package com.example.feature_favorites.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.dataclass.Course
import com.example.domain.usecase.GetFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//Manages fvorite courses state for UI.
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Course>>(emptyList())
    val favorites: StateFlow<List<Course>> = _favorites

    //Load favorites when ViewModel is created.
    init {
        loadFavoriteCourses()
    }

    //Load Favorite course from use case and update StateFlow.
    private fun loadFavoriteCourses() {
        viewModelScope.launch {
            _favorites.value = getFavouritesUseCase()
        }
    }
}