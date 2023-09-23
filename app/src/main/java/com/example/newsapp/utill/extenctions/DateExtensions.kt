package com.example.newsapp.utill.extenctions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val YYYY_MM_DD = "yyyy-MM-dd"
const val HH_MM = "h:mm a"
const val HH_MM_ss = "HH:mm:ss"
const val HH_MM_s = "h:mm:ss"
const val DD_MMMM_EEEE = "dd MMMM, EEEE"
const val DD_MMMM_yy = "dd MMMM yyyy"
const val DD_MMM_yy = "dd MMM yyyy"


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