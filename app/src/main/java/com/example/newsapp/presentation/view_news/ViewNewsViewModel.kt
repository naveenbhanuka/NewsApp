package com.example.newsapp.presentation.view_news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.utill.Constant
import com.google.gson.Gson
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

    fun getUser(context: Context): User? {
        val sharedPreferences =
            context.getSharedPreferences(Constant.PREF_USER, Context.MODE_PRIVATE)
        val gson = Gson()
        val userJson = sharedPreferences.getString(Constant.PREF_USER, null)
        return gson.fromJson(userJson, User::class.java)
    }
}