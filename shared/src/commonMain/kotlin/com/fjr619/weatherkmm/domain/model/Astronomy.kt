package com.fjr619.weatherkmm.domain.model

data class Astronomy(
    val sunriseTime: String,
    val sunsetTime: String,
    val moonriseTime: String,
    val moonsetTime: String,
    val moonPhase: String,
    val moonIllumination: Int,
    val isMoonUp: Int,
    val isSunUp: Int,
)