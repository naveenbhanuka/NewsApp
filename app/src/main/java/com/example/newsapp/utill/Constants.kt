package com.example.newsapp.utill

object Msg {
    const val TITLE_VALID_EMAIL_REQ = "Valid Email Required"
    const val VALID_EMAIL = "Please enter a valid email address"
    const val BUTTON_OK = "Ok"
    const val TITLE_VALID_PASSWORD_REQ = "Valid password Required"
    const val VALID_PASSWORD = "Password must be at least 6 characters"
    const val TITLE_VALID_NAME_REQ = "Valid name Required"
    const val VALID_NAME = "Please enter a valid name"
    const val TITLE_PASSWORD_MATCH = "Password doesn't match"
    const val MATCH_PASSWORD = "Please confirm your passwords correctly"
    const val ALERT = "Alert"
    const val ALERT_LOGIN_FAILED = "Login Failed"
    const val ALERT_REGISTRATION_FAILED = "Registration Failed"
    const val SOMETHING_WRONG = "Something went wrong..."
    const val SUCCESS = "Success"
    const val SAVE_TO_FAVOURITE = "Successfully added to Favourites"
}

object RequestCodes {
    const val LOGIN = 100
    const val REGISTRATION = 101
    const val MAIN = 102
}

object ResultCode {
    const val RESULT_NAV_MAIN = 111
    const val RESULT_NAV_LOGIN = 112
}

object Constant {
    const val DATABASE_NAME ="news_database"
    const val USER_TABLE ="user_table"
   // const val API_KEY = "bc3ae8bf9ce746988cc0dd9cfe10b442"
    const val BASE_URL = "https://newsapi.org"
    const val ARTICLE_TABLE ="article_table"
    const val API_KEY = "3b5e001b1093405c8dd7f8bd0b31083d"
}