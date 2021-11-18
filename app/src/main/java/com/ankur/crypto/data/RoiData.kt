package com.ankur.crypto.data

data class RoiData(
    val percent_change_btc_last_1_month: Double,
    val percent_change_btc_last_1_week: Double,
    val percent_change_btc_last_1_year: Double,
    val percent_change_btc_last_3_months: Double,
    val percent_change_eth_last_1_month: Double,
    val percent_change_eth_last_1_week: Double,
    val percent_change_eth_last_1_year: Double,
    val percent_change_eth_last_3_months: Double,
    val percent_change_last_1_month: Double,
    val percent_change_last_1_week: Double,
    val percent_change_last_1_year: Double,
    val percent_change_last_3_months: Double,
    val percent_change_month_to_date: Double,
    val percent_change_quarter_to_date: Double,
    val percent_change_year_to_date: Double
)