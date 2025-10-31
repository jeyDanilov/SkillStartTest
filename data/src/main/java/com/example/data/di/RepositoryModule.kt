package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.CoursesRepositoryImpl
import com.example.domain.contract.AuthRepository
import com.example.domain.contract.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Модуль DI для репозиториев
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Привязка реализации AuthRepository
    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(
        impl: CoursesRepositoryImpl
    ): CourseRepository
}
