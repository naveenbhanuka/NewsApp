package com.example.newsapp.data.datasource.dto

import java.io.Serializable

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Serializable