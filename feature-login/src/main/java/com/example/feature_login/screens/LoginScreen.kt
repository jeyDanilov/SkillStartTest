package com.example.feature_login.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feature_login.databinding.LoginScreenBinding
import com.example.feature_login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginScreen: AppCompatActivity() {

private lateinit var  binding: LoginScreenBinding
private val viewModel: LoginViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonVk.setOnClickListener {
            openBrowser("https://vk.com/")
        }

        binding.buttonClassMate.setOnClickListener {
            openBrowser("https://classmates.ru/")
        }
    }

    private fun openBrowser(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
