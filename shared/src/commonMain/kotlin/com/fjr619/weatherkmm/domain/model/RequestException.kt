package com.fjr619.weatherkmm.domain.model

class RequestException(override val message: String?, val statusCode: Int) : Exception(message)
