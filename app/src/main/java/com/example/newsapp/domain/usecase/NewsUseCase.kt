package com.example.newsapp.domain.usecase

import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun getLatestNews(countryCode: String, pageNumber: Int): Response<NewsResponse> =
        newsRepository.getLatestNews(countryCode = countryCode, pageNumber = pageNumber)

    suspend fun getAllNews(searchQuery: String?, pageNumber: Int): Response<NewsResponse> =
        newsRepository.getAllNews(searchQuery = searchQuery, pageNumber = pageNumber)
}