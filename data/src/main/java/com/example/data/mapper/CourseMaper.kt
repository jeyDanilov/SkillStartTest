package com.example.data.mapper


import com.example.data.dataclass.CourseDto
import com.example.data.room.FavoriteCourseEntity
import com.example.domain.dataclass.Course as DomainCourse

fun CourseDto.toDomain(): DomainCourse = DomainCourse(
    id = this.id,
    title = this.title ?: "",
    description = this.description ?: "",
    publishDate = this.publishDate ?: "",
    price = this.price ?: "",
    isFavorite = false
)

fun FavoriteCourseEntity.toDomain(): DomainCourse = DomainCourse(
    id = this.id,
    title = this.title ?: "",
    description = this.description ?: "",
    publishDate = this.publishDate ?: "",
    price = this.price ?: "",
    isFavorite = true
)

fun DomainCourse.toEntity(): FavoriteCourseEntity = FavoriteCourseEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    publishDate = this.publishDate,
    price = this.price
)
