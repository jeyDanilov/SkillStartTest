package com.example.feature_favorites.viewmodel.favorites.ui.adapter


import com.bumptech.glide.Glide
import com.example.domain.dataclass.Course
import com.example.feature_home.R
import com.example.feature_home.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun favoriteDelegate() =
    adapterDelegateViewBinding<Course, Course, ItemCourseBinding>(
        { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
    ) {
        bind {

            val imageRes = when (item.imageRes) {
                0 -> R.drawable.imagecardviewone
                1 -> R.drawable.imagecardviewtwo
                else -> R.drawable.imagecardviewthree
            }

            // Take picture from course model.
            item.imageRes?.let {
                Glide.with(binding.root)
                    .load(imageRes)
                    .into(binding.imageCardViewOne)
            }

            binding.textViewTitle.text = item.title
            binding.textViewPrice.text = item.price
        }
    }