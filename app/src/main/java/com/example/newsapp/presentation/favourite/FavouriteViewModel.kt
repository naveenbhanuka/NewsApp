package com.example.newsapp.presentation.favourite

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

class FavouriteViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _getSaveNewsResult = MutableLiveData<List<Article>>()
    val getSaveNewsResult: LiveData<List<Article>> = _getSaveNewsResult

    fun getSavedNews(userId: String) = viewModelScope.launch {
        viewModelScope.launch {
            val articleList = newsUseCase.getAllArticles(userId = userId)
            _getSaveNewsResult.postValue(articleList)
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