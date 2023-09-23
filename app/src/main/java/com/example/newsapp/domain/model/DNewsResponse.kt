package com.example.newsapp.domain.model

import java.io.Serializable

data class DNewsResponse(
    val articles: List<DArticle>,
    val status: String,
    val totalResults: Int
) : Serializable