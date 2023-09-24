package com.example.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.data.datasource.dto.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getLatestNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun getAllNews(searchQuery: String?, pageNumber: Int): Response<NewsResponse>

    suspend fun saveArticle(article: Article)

    suspend fun getAllArticles() : List<Article>
}