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

// Di module for repository.
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Bind implementation of AuthRepository.
    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    //Bind implementation of CoursesRepository.
    @Binds
    @Singleton
    abstract fun bindCoursesRepository(
        impl: CoursesRepositoryImpl
    ): CourseRepository
}
