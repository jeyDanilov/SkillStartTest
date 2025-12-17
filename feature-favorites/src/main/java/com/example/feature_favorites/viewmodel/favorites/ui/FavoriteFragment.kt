package com.example.feature_favorites.viewmodel.favorites.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.FavoriteFragmentBinding
import com.example.feature_favorites.viewmodel.FavoritesViewModel
import com.example.feature_favorites.viewmodel.favorites.ui.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//Displays list of favorite courses.
@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    //ViewModel injected via Hilt.
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FavoriteFragmentBinding.bind(view)

        //Initialize adapter and set up RecyclerView.
        adapter = FavoritesAdapter()
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavorites.adapter = adapter


        //Collect Flow of favorites from ViewModel.
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.collect { list ->
                adapter.setData(list)
            }
        }
    }
}
