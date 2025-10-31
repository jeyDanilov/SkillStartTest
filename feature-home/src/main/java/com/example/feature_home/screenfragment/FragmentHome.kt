package com.example.feature_home.screenfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.dataclass.Course
import com.example.feature_home.R
import com.example.feature_home.adapter.CourseAdapter
import com.example.feature_home.databinding.FragmentHomeBinding
import com.example.feature_home.viewmodel.CourseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Экран "Главная"
@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {

    private val viewModel: CourseViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = CourseAdapter()

    private var isSortedDescending = false
    private var originalList: List<Course> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        // Настройка RecyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Подписка на поток курсов
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.courses.collect { list ->
                    originalList = list
                    updateList()
                }
            }
        }

        // Обработка сортировки
        binding.imageFilter.setOnClickListener {
            isSortedDescending = !isSortedDescending
            updateList()
        }
    }

    // Обновление списка с учётом сортировки
    private fun updateList() {
        val sorted = if (isSortedDescending) {
            originalList.sortedByDescending { it.publishDate }
        } else {
            originalList
        }
        adapter.items = sorted
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}