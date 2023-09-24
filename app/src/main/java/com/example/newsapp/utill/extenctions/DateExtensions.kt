package com.example.newsapp.utill.extenctions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val YYYY_MM_DD = "yyyy-MM-dd"
const val DD_MMMM_yy = "dd MMMM yyyy"


fun String.toDate(): Date? {
    val simpleDateFormat = SimpleDateFormat(YYYY_MM_DD, Locale.US)
    return try {
        simpleDateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toCustomDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.US)
    return simpleDateFormat.format(this)
}

fun String.toCustomDate(format: String): String {
    return this.toDate()?.let {
        it.toCustomDate(format)
    } ?: ""
}