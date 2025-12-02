package com.example.feature_favorites.viewmodel.favorites.ui.adapter

import com.example.domain.dataclass.Course
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class FavoritesAdapter : ListDelegationAdapter<List<Course>>() {

    init {
        delegatesManager.addDelegate(favoriteDelegate())
    }

    fun setData(list: List<Course>) {
        this.items = list
        notifyDataSetChanged()
    }
}