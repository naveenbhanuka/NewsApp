package com.example.newsapp.presentation.login

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult

    fun loginUser(context: Context,email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userUseCase.getUserByEmailAndPassword(email = email, password = password)
                if (user != null) {
                    _loginResult.value = Result.success(user)
                    saveUserData(context = context, user = user)
                } else {
                    _loginResult.value = Result.failure(Exception("Invalid credentials"))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
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