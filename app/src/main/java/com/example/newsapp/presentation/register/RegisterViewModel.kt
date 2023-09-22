package com.example.newsapp.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userUseCase: UserUseCase
) : ViewModel(){

    private val _registrationResult = MutableLiveData<Result<Unit>>()
    val registrationResult: LiveData<Result<Unit>> = _registrationResult

    fun registerUser(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = User(firstName = firstName, lastName = lastName, email = email, password = password)
                userUseCase.register(user)
                _registrationResult.value = Result.success(Unit)
            } catch (e: Exception) {
                _registrationResult.value = Result.failure(e)
            }
        }
    }
}