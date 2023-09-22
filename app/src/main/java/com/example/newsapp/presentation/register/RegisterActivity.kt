package com.example.newsapp.presentation.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityRegisterBinding
import com.example.newsapp.utill.extenctions.setActionBar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        initUI()


    }

    private fun initUI() {
        setSupportActionBar(binding.toolbarAuthSignUp)
        supportActionBar?.setActionBar(
            this,
            getString(R.string.label_signUp),
            true
        )
    }
}