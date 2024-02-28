package com.fjr619.weatherkmm.data.repository

import com.fjr619.weatherkmm.data.model.dto.toDomain
import com.fjr619.weatherkmm.data.remote.RemoteDataSource
import com.fjr619.weatherkmm.domain.model.AirQualityEnum
import com.fjr619.weatherkmm.domain.model.CurrentWeather
import com.fjr619.weatherkmm.domain.model.RequestException
import com.fjr619.weatherkmm.domain.model.Response
import com.fjr619.weatherkmm.domain.repository.CurrentWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CurrentWeatherRepoImpl(
    private val remoteDataSource: RemoteDataSource
): CurrentWeatherRepo {
    override fun getCurrentWeather(
        query: String,
        airQualityEnum: AirQualityEnum
    ): Flow<Response<CurrentWeather>> = flow {
        try {
            val response = remoteDataSource.fetchCurrentWeather(
                query = query,
                airQuality = airQualityEnum
            )
            emit(Response.Success(response.toDomain()) as Response<CurrentWeather>)
        } catch (e: RequestException) {
            emit(Response.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}