package com.example.feature_home.adapter


import com.example.domain.dataclass.Course
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

// Адаптер списка курсов
class CourseAdapter(onFavoriteClick: (Course) -> Unit)
    : ListDelegationAdapter<List<Course>>() {
    init {
        delegatesManager.addDelegate(courseDelegate(onFavoriteClick))

    }
}