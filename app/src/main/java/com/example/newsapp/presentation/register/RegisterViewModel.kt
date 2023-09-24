package com.example.newsapp.presentation.register

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.User
import com.example.newsapp.domain.usecase.UserUseCase
import com.example.newsapp.utill.Constant
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _registrationResult = MutableLiveData<Result<Unit>>()
    val registrationResult: LiveData<Result<Unit>> = _registrationResult

    fun registerUser(context: Context,firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                userUseCase.register(user)
                saveUserData(context,user)
                _registrationResult.value = Result.success(Unit)
            } catch (e: Exception) {
                _registrationResult.value = Result.failure(e)
            }
        }
    }

    private fun saveUserData(context: Context, user: User) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(Constant.PREF_USER, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val userJson = gson.toJson(user)
        editor.putString(Constant.PREF_USER, userJson)
        editor.apply()
    }
}