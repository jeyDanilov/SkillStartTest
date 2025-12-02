package com.example.feature_favorites.viewmodel.favorites.ui.adapter


import com.bumptech.glide.Glide
import com.example.domain.dataclass.Course
import com.example.feature_home.R
import com.example.feature_home.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

private val images = listOf(
    R.drawable.imagecardviewone,
    R.drawable.imagecardviewtwo,
    R.drawable.imagecardviewthree
)
fun favoriteDelegate() =
    adapterDelegateViewBinding<Course, Course, ItemCourseBinding>(
        { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
    ) {
        bind {
            val imageRes = images[adapterPosition % images.size]
            Glide.with(binding.root)
                .load(imageRes)
                .into(binding.imageCardViewOne)

            binding.textViewTitle.text = item.title
            binding.textViewPrice.text = item.price
        }
    }