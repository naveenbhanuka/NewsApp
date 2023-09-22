package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {

    suspend fun register(user: User) = repository.register(user)

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? =
        repository.getUserByEmailAndPassword(email = email, password = password)
}