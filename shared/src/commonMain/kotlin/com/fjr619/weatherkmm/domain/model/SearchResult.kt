package com.fjr619.weatherkmm.domain.model

data class SearchResult(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
)