package com.fjr619.weatherkmm.ui.screens.today

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodayWeatherViewModel(): ViewModel() {

    private val _state = MutableStateFlow(TodayWeatherUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: TodayWeatherEvent) {
        when(event) {
            is TodayWeatherEvent.UpdateForecast -> {
                event.forecast?.let { forecast ->
                    _state.setResponse(forecast)
                }
            }
        }
    }
}