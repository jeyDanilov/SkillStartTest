package com.example.feature_favorites.viewmodel.favorites.ui.adapter

import com.example.domain.dataclass.Course
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

//Manges displaying favorite courses in RecyclerView.
class FavoritesAdapter : ListDelegationAdapter<List<Course>>() {

    //Initializes adapter with delegate for binding course items.
    init {
        delegatesManager.addDelegate(favoriteDelegate())
    }

    //Update data adapter and refresh UI.
    fun setData(list: List<Course>) {
        this.items = list
        notifyDataSetChanged()
    }
}