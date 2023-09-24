package com.example.newsapp.presentation.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.model.User
import com.example.newsapp.utill.Constant
import com.google.gson.Gson

class ProfileViewModel : ViewModel() {

    fun getUser(context: Context): User? {
        val sharedPreferences =
            context.getSharedPreferences(Constant.PREF_USER, Context.MODE_PRIVATE)
        val gson = Gson()
        val userJson = sharedPreferences.getString(Constant.PREF_USER, null)
        return gson.fromJson(userJson, User::class.java)
    }
}