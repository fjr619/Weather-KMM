package com.fjr619.weatherkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform