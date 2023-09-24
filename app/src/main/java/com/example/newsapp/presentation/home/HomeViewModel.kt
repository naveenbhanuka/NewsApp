package com.example.newsapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.utill.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel(){

    val latestNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var latestNewsPage = 1

    val getAllNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var getAllNewsPage = 1

    fun getLatestNews(pageSize: Int?) = viewModelScope.launch {
        latestNews.postValue(Resource.Loading())
        val response = newsUseCase.getLatestNews("us", latestNewsPage,pageSize)
        latestNews.postValue(handleLatestNewsResponse(response))
    }

    private fun handleLatestNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getAllNews(searchQuery: String?) = viewModelScope.launch {
        getAllNews.postValue(Resource.Loading())
        val response = newsUseCase.getAllNews(searchQuery, getAllNewsPage)
        getAllNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}