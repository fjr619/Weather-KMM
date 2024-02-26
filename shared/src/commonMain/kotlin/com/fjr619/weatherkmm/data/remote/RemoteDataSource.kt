package com.fjr619.weatherkmm.data.remote

import com.fjr619.weatherkmm.data.model.dto.CurrentWeatherResponseDto
import com.fjr619.weatherkmm.data.model.dto.ForecastResponseDto
import com.fjr619.weatherkmm.data.model.dto.SearchResultDto
import com.fjr619.weatherkmm.domain.model.AirQualityEnum
import com.fjr619.weatherkmm.domain.model.WeatherAlertsEnum

interface RemoteDataSource {
    suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQualityEnum,
    ): CurrentWeatherResponseDto

    suspend fun fetchForecast(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int,
    ): ForecastResponseDto

    suspend fun search(query: String): List<SearchResultDto>
}