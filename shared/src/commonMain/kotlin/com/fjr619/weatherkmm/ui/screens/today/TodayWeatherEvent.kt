package com.fjr619.weatherkmm.ui.screens.today

import com.fjr619.weatherkmm.domain.model.Forecast

sealed class TodayWeatherEvent {
    data class UpdateForecast(val forecast: Forecast?) : TodayWeatherEvent()
}