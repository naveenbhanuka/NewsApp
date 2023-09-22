package com.example.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.utill.Constant.USER_TABLE

@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
