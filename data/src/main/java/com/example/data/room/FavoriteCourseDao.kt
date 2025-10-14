package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface   FavoriteCourseDao {
    @Query("SELECT * FROM favorite_courses")
    suspend fun getAll(): List<FavoriteCourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(course: FavoriteCourseEntity)

    @Delete
    suspend fun remove(course: FavoriteCourseEntity)
}

