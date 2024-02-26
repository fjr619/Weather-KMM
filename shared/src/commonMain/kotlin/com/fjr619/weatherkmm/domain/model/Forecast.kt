package com.fjr619.weatherkmm.domain.model

data class Forecast(
    val location: Location,
    val currentWeather: Weather,
    val forecastDays: List<ForecastDay>,
)