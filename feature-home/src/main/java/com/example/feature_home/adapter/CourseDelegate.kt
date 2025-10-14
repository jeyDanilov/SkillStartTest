package com.example.feature_home.adapter

import com.bumptech.glide.Glide
import com.example.data.dataclass.Course
import com.example.feature_home.R
import com.example.feature_home.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding



private val images = listOf(
    R.drawable.imagecardviewone,
    R.drawable.imagecardviewtwo,
    R.drawable.imagecardviewthree
)

fun courseDelegate() = adapterDelegateViewBinding<Course, Course, ItemCourseBinding>(
    viewBinding = { inflater, parent ->
        ItemCourseBinding.inflate(inflater, parent, false)
    }
) {
    bind {
        val imageRes = images[adapterPosition % images.size]

        Glide.with(binding.root).clear(binding.imageCardViewOne)

        Glide.with(binding.root)
            .load(imageRes)
            .into(binding.imageCardViewOne)

        binding.textViewTitle.text = item.title
        binding.textViewPrice.text = item.price

    }
}