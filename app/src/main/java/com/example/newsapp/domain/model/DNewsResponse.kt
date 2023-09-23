package com.example.newsapp.domain.model

data class DNewsResponse(
    val articles: List<DArticle>,
    val status: String,
    val totalResults: Int
)