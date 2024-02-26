package com.fjr619.weatherkmm.data.model.dao

data class LocationDao(
    val id: Long,
    val location: String,
    val createdAt: Long,
)

fun asLocationDao(
    id: Long,
    location: String,
    createdAt: Long,
): LocationDao = LocationDao(id, location, createdAt)