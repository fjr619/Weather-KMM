package com.fjr619.weatherkmm.ui.screens.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fjr619.weatherkmm.MR
import com.fjr619.weatherkmm.ui.components.CurrentDateItem
import com.fjr619.weatherkmm.ui.components.CurrentDetailsItem
import com.fjr619.weatherkmm.ui.components.CurrentWeatherItem
import com.fjr619.weatherkmm.ui.components.DividerItem
import com.fjr619.weatherkmm.ui.components.HourlyRainItem
import com.fjr619.weatherkmm.ui.components.HourlyWeatherItem
import com.fjr619.weatherkmm.ui.components.PrecipitationChanceItem
import com.fjr619.weatherkmm.ui.components.RiseAndSetItem
import com.fjr619.weatherkmm.ui.components.TotalDailyRainVolume
import com.fjr619.weatherkmm.ui.components.WindItem
import com.fjr619.weatherkmm.ui.components.WindToday
import com.fjr619.weatherkmm.ui.screens.main.MainEvent
import com.fjr619.weatherkmm.ui.screens.main.MainUiState
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TodayWeatherScreen(
    modifier: Modifier = Modifier,
    mainUiState: MainUiState,
    updateMainUiState: (MainEvent) -> Unit
) {
    val viewModel = getViewModel(
        key = "today-vm",
        factory = viewModelFactory { TodayWeatherViewModel() }
    )

    val state by viewModel.state.collectAsState()

    when {
        mainUiState.isLoading -> {
            Text(text = "LOADING")
        }

        mainUiState.isError -> {
            Text(text = "ERROR")
        }

        else -> {
            TodayWeatherContent(state)
        }
    }

    LaunchedEffect(mainUiState.forecast) {
        mainUiState.forecast?.let {
            viewModel.onEvent(TodayWeatherEvent.UpdateForecast(it))

        }
        updateMainUiState(MainEvent.Loading(false))
    }
}

@Composable
fun TodayWeatherContent(
    state: TodayWeatherUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CurrentDateItem(state.currentTime)
        }

        item {
            CurrentWeatherItem(state)
        }

        item {
            HourlyWeatherItem(hourlyForecasts = state.hourlyForecasts)
        }

        item {
            PrecipitationChanceItem(
                rainChance = stringResource(
                    MR.strings.today_rain_chance,
                    state.rainChance,
                )
            )
        }

        item {
            DividerItem()
        }

        item {
            CurrentDetailsItem(state)
        }

        item {
            DividerItem()
        }

        item {
            HourlyRainItem(
                hourlyForecasts = state.hourlyForecasts,
            )
        }

        item {
            TotalDailyRainVolume(
                totalPrecipitation = stringResource(
                    MR.strings.total_precipitation_mm,
                    state.totalPrecipitation,
                ),
            )
        }

        item {
            DividerItem()
        }

        item {
            WindItem(hourlyForecasts = state.hourlyForecasts) {
                WindToday(todayWeatherUiState = state)
            }
        }

        item {
            DividerItem()
        }

        item {
            RiseAndSetItem(
                setTime = state.sunset,
                riseTime = state.sunrise,
                riseTitle = stringResource(MR.strings.sunrise),
                setTitle = stringResource(MR.strings.sunset),
                sectionTitle = stringResource(MR.strings.sunrise_sunset),
            )
        }

        item {
            DividerItem()
        }

        item {
            RiseAndSetItem(
                setTime = state.moonset,
                riseTime = state.moonrise,
                riseTitle = stringResource(MR.strings.moonrise),
                setTitle = stringResource(MR.strings.moonset),
                sectionTitle = stringResource(MR.strings.moonrise_moonset),
            )
        }

        item {
            DividerItem()
        }
    }
}

