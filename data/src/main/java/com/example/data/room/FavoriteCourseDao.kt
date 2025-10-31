package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO для избранных курсов
@Dao
interface FavoriteCourseDao {

    @Query("SELECT * FROM favorite_courses")
    suspend fun getFavorites(): List<FavoriteCourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(course: FavoriteCourseEntity)

    @Delete
    suspend fun remove(course: FavoriteCourseEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_courses WHERE id = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean
}