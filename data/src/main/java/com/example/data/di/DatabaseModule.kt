package com.example.data.di
import android.content.Context
import androidx.room.Room
import com.example.data.room.AppDatabase
import com.example.data.room.FavoriteCourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "skillstart-db" // Or whatever you named your database
        ).build()
    }

    @Provides
    @Singleton // Or the appropriate scope
    fun provideFavoriteCourseDao(appDatabase: AppDatabase): FavoriteCourseDao {
        return appDatabase.favoriteDao()
    }
}