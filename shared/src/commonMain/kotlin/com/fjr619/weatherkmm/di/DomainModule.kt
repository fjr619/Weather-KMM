package com.fjr619.weatherkmm.di

import com.fjr619.weatherkmm.data.repository.CurrentWeatherRepoImpl
import com.fjr619.weatherkmm.data.repository.ForecaseRepoImpl
import com.fjr619.weatherkmm.domain.model.CurrentWeather
import com.fjr619.weatherkmm.domain.repository.CurrentWeatherRepo
import com.fjr619.weatherkmm.domain.repository.ForecastRepo
import org.koin.dsl.module

val domainMidule = module {
    factory<CurrentWeatherRepo> { CurrentWeatherRepoImpl(get()) }
    factory<ForecastRepo> { ForecaseRepoImpl(get()) }
}