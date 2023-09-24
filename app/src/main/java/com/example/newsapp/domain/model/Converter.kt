package com.example.newsapp.domain.model

import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.data.datasource.dto.NewsResponse


fun Article.mapToDomain() : DArticle =
    DArticle(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )

fun List<Article>?.mapToDomainArticleList(): MutableList<DArticle> {
    val mList: MutableList<DArticle> = arrayListOf()
    this?.forEach {
        mList.add(it.mapToDomain())
    }
    return mList
}


fun NewsResponse.mapToDomain() : DNewsResponse =
    DNewsResponse(
        articles = articles.mapToDomainArticleList(),
        status = status,
        totalResults = totalResults
    )