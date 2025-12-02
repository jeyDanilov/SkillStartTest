package com.example.skillstarttest

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Инициализация экрана и настройка отступов для системных панелей
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as androidx.navigation.fragment.NavHostFragment
        navHostFragment.navController
    }

    // Обработка клика по вкладке "Главная"
    fun onHomeClick(view: View) {
    navController.navigate(com.example.feature_navigation.R.id.nav_home)
    }

    // Обработка клика по вкладке "Избранное"
    fun onFavoritesClick(view: View) {
    navController.navigate(com.example.feature_navigation.R.id.nav_favorites)

    }
}