package com.fjr619.weatherkmm.domain.model

enum class AirQualityEnum(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class WeatherAlertsEnum(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class TimeOfDayEnum {
    Day, Night,
}