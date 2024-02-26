package com.fjr619.weatherkmm.domain.repository

import com.fjr619.weatherkmm.domain.model.AirQualityEnum
import com.fjr619.weatherkmm.domain.model.Forecast
import com.fjr619.weatherkmm.domain.model.Response
import com.fjr619.weatherkmm.domain.model.WeatherAlertsEnum
import kotlinx.coroutines.flow.Flow

interface ForecastRepo {
    suspend operator fun invoke(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int,
    ): Flow<Response<Forecast>>
}