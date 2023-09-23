package com.example.newsapp.domain.model

import com.example.newsapp.data.datasource.dto.Source

data class DArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)