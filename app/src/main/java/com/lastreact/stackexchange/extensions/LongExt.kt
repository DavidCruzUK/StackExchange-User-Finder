package com.lastreact.stackexchange.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_PATTERN = "yyyy.MM.dd"

@SuppressLint("SimpleDateFormat")
fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat(DATE_PATTERN)
    return format.format(date)
}