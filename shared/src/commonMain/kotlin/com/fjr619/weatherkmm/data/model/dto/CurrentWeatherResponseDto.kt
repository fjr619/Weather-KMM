package com.fjr619.weatherkmm.data.model.dto

import com.fjr619.weatherkmm.domain.model.CurrentWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponseDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val currentWeather: WeatherDto,
)

internal fun CurrentWeatherResponseDto.toDomain() = CurrentWeather(
    location = location.toDomain(),
    currentWeather = currentWeather.toDomain(),
)