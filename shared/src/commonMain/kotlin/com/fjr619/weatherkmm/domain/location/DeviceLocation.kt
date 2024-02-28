package com.fjr619.weatherkmm.domain.location

data class DeviceLocation(
    val latitude: Double,
    val longitude: Double,
) {
    override fun toString(): String {
        return "$latitude,$longitude"
    }
}