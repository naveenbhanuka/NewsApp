package com.example.newsapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun getLatestNews(countryCode: String, pageNumber: Int,pageSize: Int?): Response<NewsResponse> =
        newsRepository.getLatestNews(countryCode = countryCode, pageNumber = pageNumber, pageSize = pageSize)

    suspend fun getAllNews(searchQuery: String?, pageNumber: Int): Response<NewsResponse> =
        newsRepository.getAllNews(searchQuery = searchQuery, pageNumber = pageNumber)

    suspend fun saveArticle(article: Article) = newsRepository.saveArticle(article= article)

    suspend fun getAllArticles(userId: String) : List<Article> = newsRepository.getAllArticles(userId = userId)
}