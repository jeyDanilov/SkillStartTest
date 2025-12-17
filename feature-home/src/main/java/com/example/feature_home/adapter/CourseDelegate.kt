package com.example.feature_home.adapter

import androidx.core.graphics.green
import com.bumptech.glide.Glide
import com.example.domain.dataclass.Course
import com.example.feature_home.R
import com.example.feature_home.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


// Images list for course.
private val images = listOf(
    R.drawable.imagecardviewone,
    R.drawable.imagecardviewtwo,
    R.drawable.imagecardviewthree
)

// Delegate adapter for course.
fun courseDelegate(onFavoriteClick: (Course) -> Unit) =
    adapterDelegateViewBinding<Course, Course, ItemCourseBinding>(
        viewBinding = { inflater, parent ->
            ItemCourseBinding.inflate(inflater, parent, false)
        }
    ) {
        binding.imageFavorites.setOnClickListener {
            onFavoriteClick(item)
        }
        bind {
            binding.imageFavorites.setImageResource(
                if (item.isFavorite) R.drawable.ic_fav_filled
                else R.drawable.vectorfav
            )

            val imageRes = images[adapterPosition % images.size]

            Glide.with(binding.root).clear(binding.imageCardViewOne)

            Glide.with(binding.root)
                .load(imageRes)
                .into(binding.imageCardViewOne)

            binding.textViewTitle.text = item.title
            binding.textViewPrice.text = item.price
        }
    }
