package com.fjr619.weatherkmm.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    @SerialName("text") val condition: String,
    @SerialName("icon") val iconUrl: String,
    @SerialName("code") val code: Int,
)