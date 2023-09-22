package com.example.newsapp

import android.app.Application
import com.example.newsapp.data.datasource.NewsDatabase
import com.example.newsapp.data.repository.UserRepositoryImpl
import com.example.newsapp.di.injectFeatures
import com.example.newsapp.domain.repository.UserRepository
import com.example.newsapp.domain.usecase.UserUseCase
import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            injectFeatures()
        }
    }

}
