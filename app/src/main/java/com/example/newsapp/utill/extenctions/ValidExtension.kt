package com.example.newsapp.utill.extenctions

import android.util.Patterns

/**
 * validate email
 */
fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()

/**
 * validate mobile
 */
fun String.isValidPhone(): Boolean = this.isNotEmpty() &&
        Patterns.PHONE.matcher(this.trim()).matches()

fun String.isValidPassword(): Boolean {
    var valid: Boolean
    try {
        valid = this.length >= 6
    } catch (e: Exception) {
        valid = false
        e.printStackTrace()
    }

    return valid
}

fun String.matchTo(
    string: String
): Boolean {
    return this == string
}

fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}
