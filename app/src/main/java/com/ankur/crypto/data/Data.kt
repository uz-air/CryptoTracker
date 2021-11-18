package com.ankur.crypto.data

data class Data(
    val _internal_temp_agora_id: String,
    val contract_addresses: List<Any>,
    val id: String,
    val metrics: Metrics,
    val name: String,
    val slug: String,
    val symbol: String
)