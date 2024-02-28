package com.fjr619.weatherkmm.ui.screens.today

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fjr619.weatherkmm.ui.screens.main.MainUiState

@Composable
fun TodayWeatherScreen(
    modifier: Modifier = Modifier,
    mainUiState: MainUiState
) {
    when {
        mainUiState.isLoading -> { Text(text = "LOADING") }
        mainUiState.isError -> { Text(text = "ERROR") }
        else -> {
            Text(text = "SHOW CONTENT current weather in celcius ${mainUiState.forecast?.currentWeather?.temperatureCelsius}")
        }
    }
}