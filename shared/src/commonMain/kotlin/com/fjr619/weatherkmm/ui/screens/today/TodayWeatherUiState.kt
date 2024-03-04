package com.fjr619.weatherkmm.ui.screens.today

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.fjr619.weatherkmm.domain.model.Forecast
import com.fjr619.weatherkmm.domain.model.ForecastDay
import com.fjr619.weatherkmm.domain.model.Hour
import com.fjr619.weatherkmm.ui.model.HourUi
import com.fjr619.weatherkmm.ui.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class TodayWeatherUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val currentTime: String = "",
    val currentTemperature: String = "",
    val feelsLikeTemperature: String = "",
    val condition: String = "",
    val conditionIconUrl: String = "",
    val hourlyForecasts: List<HourUi> = emptyList(),
)

internal fun MutableStateFlow<TodayWeatherUiState>.setResponse(result: Forecast) {
    val allDaysHours = getForecastHours(
        forecastDays = result.forecastDays,
        currentTimeEpochSec = result.location.localTimeEpoch,
        timeZoneId = result.location.timeZoneId,

        )

    update {
        it.copy(
            isError = false,
            isLoading = false,
            currentTime = formatTime(
                currentTime = parseTime(result.location.localTimeEpoch, result.location.timeZoneId)
            ),
            currentTemperature = result.currentWeather.temperatureCelsius.toInt().toString(),
            feelsLikeTemperature = result.currentWeather.feelsLikeTemperatureCelsius.toInt()
                .toString(),
            condition = result.currentWeather.weatherCondition.condition,
            conditionIconUrl = "https:${result.currentWeather.weatherCondition.iconUrl}",
            hourlyForecasts = allDaysHours
        )
    }
}

fun parseTime(localTimeEpoch: Long, timeZoneId: String) =
    Instant.fromEpochSeconds(localTimeEpoch).toLocalDateTime(TimeZone.of(timeZoneId))

fun formatTime(currentTime: LocalDateTime): String {
    return "${currentTime.dayOfMonth} ${
        currentTime.month.name.lowercase().capitalize(
            Locale.current
        )
    } ${currentTime.time.hour}:${currentTime.time.minute}"
}

fun isSameDay(currentTimeEpoch: Long, localTimeEpoch: Long, timeZoneId: String): Boolean {
    val currentDateTime = parseTime(currentTimeEpoch, timeZoneId)
    val localTime = parseTime(localTimeEpoch, timeZoneId)

    return currentDateTime.dayOfMonth == localTime.dayOfMonth &&
            currentDateTime.monthNumber == localTime.monthNumber &&
            currentDateTime.year == localTime.year
}

fun getHour(timeEpoch: Long, timeZoneId: String) = parseTime(
    timeEpoch, timeZoneId
).hour

fun getForecastHours(
    forecastDays: List<ForecastDay>,
    timeZoneId: String,
    currentTimeEpochSec: Long
): List<HourUi> {
    val hours = mutableListOf<Hour>()
    forecastDays.map {
        println("${it.hours.size} ${it.date}")
        hours.addAll(it.hours)
    }

    return hours.filter {hour ->
        val isSameDay = isSameDay(
            currentTimeEpoch = currentTimeEpochSec,
            localTimeEpoch = hour.timeEpoch,
            timeZoneId = timeZoneId,
        )

        isSameDay && getHour(hour.timeEpoch, timeZoneId) >= getHour(
            currentTimeEpochSec,
            timeZoneId
        )
    }.map { hour -> hour.toUi() }
}