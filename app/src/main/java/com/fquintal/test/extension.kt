package com.fquintal.test

import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(pattern: String): Date {
    val formatter = SimpleDateFormat(pattern)
    return formatter.parse(this)
}

fun Date.toString(pattern: String): String {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}