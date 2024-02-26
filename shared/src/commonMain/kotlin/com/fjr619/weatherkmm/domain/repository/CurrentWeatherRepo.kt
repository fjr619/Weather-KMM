package com.fjr619.weatherkmm.domain.repository

import com.fjr619.weatherkmm.domain.model.AirQualityEnum
import com.fjr619.weatherkmm.domain.model.CurrentWeather
import com.fjr619.weatherkmm.domain.model.Response
import kotlinx.coroutines.flow.Flow


interface CurrentWeatherRepo {
    fun getCurrentWeather(
        query: String,
        airQualityEnum: AirQualityEnum
    ): Flow<Response<CurrentWeather>>
}