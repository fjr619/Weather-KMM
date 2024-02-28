package com.fjr619.weatherkmm.domain.location

interface LocationService {
    suspend fun getCurrentLocation(): DeviceLocation
}