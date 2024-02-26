package com.fjr619.weatherkmm.data.repository

import com.fjr619.weatherkmm.data.model.dto.toDomain
import com.fjr619.weatherkmm.data.remote.RemoteDataSource
import com.fjr619.weatherkmm.domain.model.AirQualityEnum
import com.fjr619.weatherkmm.domain.model.Forecast
import com.fjr619.weatherkmm.domain.model.Response
import com.fjr619.weatherkmm.domain.model.WeatherAlertsEnum
import com.fjr619.weatherkmm.domain.repository.ForecastRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ForecaseRepoImpl(
    private val remoteDataSource: RemoteDataSource
): ForecastRepo {
    override suspend fun invoke(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int
    ): Flow<Response<Forecast>> = flow {
        val response = remoteDataSource.fetchForecast(
            query = query,
            airQuality = airQuality,
            weatherAlerts = weatherAlerts,
            days = days
        )

        emit(Response.Success(response.toDomain()) as Response<Forecast>)
    }.catch {
        emit(Response.Error(it))
    }.flowOn(Dispatchers.IO)
}