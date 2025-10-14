package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO для избранных курсов
@Dao
interface FavoriteCourseDao {

    // Получить все избранные
    @Query("SELECT * FROM favorite_courses")
    suspend fun getAll(): List<FavoriteCourseEntity>

    // Добавить в избранное
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(course: FavoriteCourseEntity)

    // Удалить из избранного
    @Delete
    suspend fun remove(course: FavoriteCourseEntity)
}