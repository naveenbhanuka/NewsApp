package com.example.newsapp.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _getSaveNewsResult = MutableLiveData<List<Article>>()
    val getSaveNewsResult: LiveData<List<Article>> = _getSaveNewsResult

    fun getSavedNews() = viewModelScope.launch {
        viewModelScope.launch {
            val articleList = newsUseCase.getAllArticles()
            _getSaveNewsResult.postValue(articleList)
        }
    }
}