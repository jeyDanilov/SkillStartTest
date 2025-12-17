package com.example.data.di


import com.example.data.intarface.AuthApi
import com.example.data.intarface.CourseApi
import com.example.data.repository.CoursesRepositoryImpl
import com.example.data.room.FavoriteCourseDao
import com.example.domain.contract.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Dependency injection module for network components
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL for API.
    private const val BASE_URL = "https://jeydanilov.github.io/SkillStartData/"

    // Redirect interceptor.
    @Provides
    @Singleton
    fun provideRedirectInterceptor(): Interceptor = Interceptor { chain ->
        var request = chain.request()
        var response: Response
        var tryCount = 0

        while (tryCount < 10) {
            response = chain.proceed(request)

            if (!response.isRedirect) return@Interceptor response

            response.use {
                val newUrl = it.header("Location") ?: return@Interceptor response
                request = request.newBuilder().url(newUrl).build()
            }
            tryCount++
        }

        chain.proceed(request)
    }

    // HTTP-client with redirect support.
    @Provides
    @Singleton
    fun provideOkHttpClient(redirectInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(redirectInterceptor)
            .build()

    // Retrofit-instance.
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Authorization API.
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    // Courses API.
    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi =
        retrofit.create(CourseApi::class.java)
}
