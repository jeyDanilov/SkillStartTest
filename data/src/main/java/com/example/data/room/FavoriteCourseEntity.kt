package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// Сущность избранного курса
@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity(
    @PrimaryKey val courseId: Int
)