package com.fquintal.model

import java.text.DecimalFormat
import java.text.NumberFormat

class Movement constructor(
    val id: String,
    private val amount: Double,
    val customDescription: String,
    val description: String,
    val date: String,
    val account: Account
) {

    fun toFormat(): String {
        val formatter: NumberFormat = DecimalFormat("$#,###")
        return formatter.format(amount)
    }
}