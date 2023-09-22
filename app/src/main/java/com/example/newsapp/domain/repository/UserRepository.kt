package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.User

interface UserRepository {

    suspend fun register(user: User)

    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

}