package com.example.data.mapper


import com.example.data.dataclass.CourseDto
import com.example.data.room.FavoriteCourseEntity
import com.example.domain.dataclass.Course as DomainCourse

//Convert CourseDto to DomainCourse.
fun CourseDto.toDomain(): DomainCourse = DomainCourse(
    id = this.id,
    title = this.title ?: "",
    description = this.description ?: "",
    publishDate = this.publishDate ?: "",
    price = this.price ?: "",
    imageRes = id % 3,
    isFavorite = false
)

//Convert FavoriteCourseEntity to DomainCourse.
fun FavoriteCourseEntity.toDomain(): DomainCourse = DomainCourse(
    id = this.id,
    title = this.title ?: "",
    description = this.description ?: "",
    publishDate = this.publishDate ?: "",
    price = this.price ?: "",
    imageRes = imageRes,
    isFavorite = true
)

//Convert DomainCourse back to FavoriteCourseEntity.
fun DomainCourse.toEntity(): FavoriteCourseEntity = FavoriteCourseEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    publishDate = this.publishDate,
    imageRes = imageRes,
    price = this.price
)
