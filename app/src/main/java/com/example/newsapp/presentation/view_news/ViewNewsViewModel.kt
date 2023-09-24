package com.example.newsapp.presentation.view_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class ViewNewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _saveArticleResult = MutableLiveData<Result<Unit>>()
    val saveArticleResult: LiveData<Result<Unit>> = _saveArticleResult

    fun saveArticle(article: Article) = viewModelScope.launch {
        try {
            newsUseCase.saveArticle(article)
            _saveArticleResult.value = Result.success(Unit)
        } catch (e: Exception) {
            _saveArticleResult.value = Result.failure(e)
        }
    }
}