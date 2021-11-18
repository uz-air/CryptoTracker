package com.ankur.crypto.data

data class AllTimeHigh(
    val at: String,
    val breakeven_multiple: Double,
    val days_since: Double,
    val percent_down: Double,
    val price: Double
)