package com.example.newsapp.data.repository

import com.example.newsapp.data.datasource.UserDao
import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao : UserDao
) : UserRepository {

    override suspend fun register(user: User)  = dao.register(user)

    override suspend fun getUserByEmailAndPassword(email: String, password: String): User? =
        dao.getUserByEmailAndPassword(email = email, password = password)
}