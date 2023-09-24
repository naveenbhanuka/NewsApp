package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.datasource.ArticleDao
import com.example.newsapp.data.datasource.NewsApi
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getLatestNews(
        countryCode: String,
        pageNumber: Int,
        pageSize: Int?
    ): Response<NewsResponse> =
        newsApi.getLatestNews(countryCode = countryCode, pageNumber = pageNumber, pageSize = pageSize)

    override suspend fun getAllNews(
        searchQuery: String?,
        pageNumber: Int
    ): Response<NewsResponse> =
        newsApi.getAllNews(searchQuery = searchQuery, pageNumber = pageNumber)

    override suspend fun saveArticle(article: Article) = articleDao.saveArticle(article = article)
    override suspend fun getAllArticles(): List<Article> =
        articleDao.getAllArticles()

}