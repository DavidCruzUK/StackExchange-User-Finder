package com.lastreact.stackexchange.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_PATTERN = "DD-MM-YYY"

/**
 * Long Nummber is multiply by 1000 as required to correct
 * for unix epoch time
 * @link: https://api.stackexchange.com/docs/dates
 */
@SuppressLint("SimpleDateFormat")
fun Long.convertLongToTime(): String {
    val date = Date(this * 1000L)
    val format = SimpleDateFormat(DATE_PATTERN)
    return format.format(date)
}