package com.fjr619.weatherkmm.data.remote.request

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("search.json")
data class SearchRequest(
    @SerialName("q") val query: String,
)