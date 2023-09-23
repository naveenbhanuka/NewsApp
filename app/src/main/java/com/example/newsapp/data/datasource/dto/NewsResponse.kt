package com.example.newsapp.data.datasource.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)