package com.ankur.crypto.data

data class DeveloperActivity(
    val commits_last_1_year: Any,
    val commits_last_3_months: Any,
    val lines_added_last_1_year: Any,
    val lines_added_last_3_months: Any,
    val lines_deleted_last_1_year: Any,
    val lines_deleted_last_3_months: Any,
    val stars: Double,
    val watchers: Int
)