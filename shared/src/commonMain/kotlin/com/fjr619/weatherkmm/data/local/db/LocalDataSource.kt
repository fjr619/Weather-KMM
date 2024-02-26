package com.fjr619.weatherkmm.data.local.db

import com.fjr619.weatherkmm.data.model.LocationDao
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getLocations(): Flow<List<LocationDao>>
    suspend fun insertLocation(location: LocationDao)
    suspend fun deleteLocation(id: Long)
    suspend fun getLocationById(location: String): List<LocationDao>
}