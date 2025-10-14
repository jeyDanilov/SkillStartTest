package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.contract.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Модуль DI для репозиториев
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Привязка реализации AuthRepository
    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}