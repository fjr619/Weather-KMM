package com.fjr619.weatherkmm.data.model.dto

import com.fjr619.weatherkmm.domain.model.Forecast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponseDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val currentWeather: WeatherDto,
    @SerialName("forecast") val forecast: ForecastDto,
)

internal fun ForecastResponseDto.toDomain() = Forecast(
    location = location.toDomain(),
    currentWeather = currentWeather.toDomain(),
    forecastDays = forecast.forecastDays.map { day -> day.toDomain() },
)