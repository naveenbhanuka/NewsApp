package com.example.newsapp.data.datasource.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.utill.Constant
import java.io.Serializable

@Entity(tableName = Constant.ARTICLE_TABLE)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    var userId: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable