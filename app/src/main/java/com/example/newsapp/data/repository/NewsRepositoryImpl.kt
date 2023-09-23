package com.example.newsapp.data.repository

import com.example.newsapp.data.datasource.NewsApi
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getLatestNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> =
        newsApi.getLatestNews(countryCode = countryCode, pageNumber = pageNumber)

    override suspend fun getAllNews(
        searchQuery: String?,
        pageNumber: Int
    ): Response<NewsResponse> =
        newsApi.getAllNews(searchQuery = searchQuery, pageNumber = pageNumber)
}