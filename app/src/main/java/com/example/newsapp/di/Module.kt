package com.example.newsapp.di

import com.example.newsapp.data.datasource.NewsDatabase
import com.example.newsapp.data.datasource.RetrofitInstance
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.repository.UserRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.repository.UserRepository
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.domain.usecase.UserUseCase
import com.example.newsapp.presentation.favourite.FavouriteViewModel
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.presentation.profile.ProfileViewModel
import com.example.newsapp.presentation.register.RegisterViewModel
import com.example.newsapp.presentation.view_news.ViewNewsViewModel
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
    single { RetrofitInstance.api }
    single { get<NewsDatabase>().articleDao() }
}

val viewModelModule: Module = module {
    viewModel { LoginViewModel(userUseCase = get()) }
    viewModel { RegisterViewModel(userUseCase = get()) }
    viewModel { HomeViewModel(newsUseCase = get()) }
    viewModel { ViewNewsViewModel(newsUseCase = get()) }
    viewModel { FavouriteViewModel(newsUseCase = get()) }
    viewModel { ProfileViewModel() }
}

val useCaseModule: Module = module {
    factory { UserUseCase(repository = get()) }
    factory { NewsUseCase( newsRepository = get()) }
}

val repositoryModule: Module = module {
    single<UserRepository> {
        UserRepositoryImpl(
            dao = get()
        )
    }
    single<NewsRepository> {
        NewsRepositoryImpl(
            newsApi = get(),
            articleDao = get()
        )
    }
}
