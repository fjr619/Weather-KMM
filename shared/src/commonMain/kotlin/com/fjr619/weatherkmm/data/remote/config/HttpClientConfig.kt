package com.fjr619.weatherkmm.data.remote.config

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    expectSuccess = true
    install(Resources)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, request ->
            val type = when (exception) {
                is ClientRequestException -> RemoteExceptionType.CLIENT_ERROR
                is ServerResponseException -> RemoteExceptionType.SERVER_ERROR
                is JsonConvertException -> RemoteExceptionType.PARSING_ERROR
                else -> RemoteExceptionType.UNKNOWN
            }
            throw RemoteException(type)
        }
    }

    defaultRequest {
        url {
            host = "api.weatherapi.com/v1/"
            protocol = URLProtocol.HTTPS
            parameters.append("key", "07e8be4b8bff49d999073159242602")
            contentType(ContentType.Application.Json)
        }
    }
}

const val apiversion = "v1"

enum class AirQuality(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class WeatherAlerts(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class RemoteExceptionType {
    SERVER_ERROR,
    CLIENT_ERROR,
    PARSING_ERROR,
    UNKNOWN,
}

class RemoteException(val type: RemoteExceptionType) : Exception()