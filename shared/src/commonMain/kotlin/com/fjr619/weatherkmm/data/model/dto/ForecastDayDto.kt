package com.fjr619.weatherkmm.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    @SerialName("date") val date: String,
    @SerialName("date_epoch") val dateEpoch: Long,
    @SerialName("day") val day: DayDto,
    @SerialName("astro") val astronomy: AstronomyDto,
    @SerialName("hour") val hours: List<HourDto>,
)