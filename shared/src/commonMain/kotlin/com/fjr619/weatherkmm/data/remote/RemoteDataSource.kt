package com.fjr619.weatherkmm.data.remote

import com.fjr619.weatherkmm.data.model.dto.CurrentWeatherResponseDto
import com.fjr619.weatherkmm.data.model.dto.ForecastResponseDto
import com.fjr619.weatherkmm.data.model.dto.SearchResultDto
import com.fjr619.weatherkmm.data.remote.config.AirQuality
import com.fjr619.weatherkmm.data.remote.config.WeatherAlerts

interface RemoteDataSource {
    suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQuality,
    ): CurrentWeatherResponseDto

    suspend fun fetchForecast(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int,
    ): ForecastResponseDto

    suspend fun search(query: String): List<SearchResultDto>
}