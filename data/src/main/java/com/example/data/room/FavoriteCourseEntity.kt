package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

//Table in Room.
@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val publishDate: String,
)