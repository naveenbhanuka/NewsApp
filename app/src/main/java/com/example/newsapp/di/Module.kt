package com.example.newsapp.di

import com.example.newsapp.data.datasource.NewsDatabase
import com.example.newsapp.data.repository.UserRepositoryImpl
import com.example.newsapp.domain.repository.UserRepository
import com.example.newsapp.domain.usecase.UserUseCase
import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeatures() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            useCaseModule,
            repositoryModule
        )
    )
}

val networkModule = module {
    single { NewsDatabase.getDatabase(androidContext()) }
    single { get<NewsDatabase>().userDao() }
}

val viewModelModule: Module = module {
    viewModel { LoginViewModel(userUseCase = get()) }
    viewModel { RegisterViewModel(userUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { UserUseCase(repository = get()) }
}

val repositoryModule: Module = module {
    single<UserRepository> {
        UserRepositoryImpl(
            dao = get()
        )
    }
}
