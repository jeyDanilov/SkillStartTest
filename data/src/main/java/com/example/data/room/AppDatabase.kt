package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// DB room.
@Database(entities = [FavoriteCourseEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {


    // DAO for favorites.
    abstract fun favoriteDao(): FavoriteCourseDao

    // Singleton instance.
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )   .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}