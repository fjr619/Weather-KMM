package com.fjr619.weatherkmm.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponseDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val currentWeather: WeatherDto,
    @SerialName("forecast") val forecast: ForecastDto,
)