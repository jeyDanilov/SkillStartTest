package com.example.feature_home.adapter


import com.example.data.dataclass.Course
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

// Адаптер списка курсов
class CourseAdapter : ListDelegationAdapter<List<Course>>() {
    init {
        delegatesManager.addDelegate(courseDelegate())

    }
}