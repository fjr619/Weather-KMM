package com.fjr619.weatherkmm.data.remote

import com.fjr619.weatherkmm.data.model.dto.CurrentWeatherResponseDto
import com.fjr619.weatherkmm.data.model.dto.ForecastResponseDto
import com.fjr619.weatherkmm.data.model.dto.SearchResultDto
import com.fjr619.weatherkmm.data.remote.config.AirQuality
import com.fjr619.weatherkmm.data.remote.config.WeatherAlerts
import com.fjr619.weatherkmm.data.remote.request.CurrentWeatherRequest
import com.fjr619.weatherkmm.data.remote.request.ForecastRequest
import com.fjr619.weatherkmm.data.remote.request.SearchRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
): RemoteDataSource {
    override suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQuality
    ): CurrentWeatherResponseDto {
        return httpClient.get(
            CurrentWeatherRequest(
                query = query,
                airQuality = airQuality.value
            )
        ).body()
    }

    override suspend fun fetchForecast(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int
    ): ForecastResponseDto {
        return httpClient.get(
            ForecastRequest(
                query = query,
                airQuality = airQuality.value,
                alerts = weatherAlerts.value,
                days = days.coerceAtMost(3)
            )
        ).body()
    }

    override suspend fun search(query: String): List<SearchResultDto> {
        return httpClient.get(
            SearchRequest(
                query = query
            )
        ).body()
    }
}