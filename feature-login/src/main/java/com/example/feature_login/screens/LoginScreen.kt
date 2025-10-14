package com.example.feature_login.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.feature_login.databinding.LoginScreenBinding
import com.example.feature_login.viewmodel.LoginState
import com.example.feature_login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.jvm.java


@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {

    private lateinit var binding: LoginScreenBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonVk.setOnClickListener {
            openBrowser("https://vk.com/")
        }

        binding.buttonClassMate.setOnClickListener {
            openBrowser("https://ok.ru/ ")
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.login(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }


        lifecycleScope.launchWhenStarted {
            viewModel.isLoginEnabled.collect { enabled ->
                binding.buttonLogin.isEnabled = enabled
            }
        }

        binding.emailEditText.doAfterTextChanged {
            viewModel.onInputChanged(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        binding.passwordEditText.doAfterTextChanged {
            viewModel.onInputChanged(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Success -> {
                        val intent = Intent(this@LoginScreen, Class.forName("com.example.skillstarttest.MainActivity"))
                        startActivity(intent)
                        finish()
                    }
                    is LoginState.Error -> {
                        Toast.makeText(this@LoginScreen,state.message, Toast.LENGTH_SHORT).show()
                    }
                    is LoginState.Loading -> {
                        // Show loading indicator
                    }

                    else -> {}
                }
            }
        }
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
