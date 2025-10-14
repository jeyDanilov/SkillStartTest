package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// База данных Room
@Database(entities = [FavoriteCourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // DAO для избранного
    abstract fun favoriteDao(): FavoriteCourseDao

    // Синглтон-инстанс
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}