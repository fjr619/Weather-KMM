package com.fjr619.weatherkmm.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.fjr619.weatherkmm.WeatherDatabase
import com.fjr619.weatherkmm.data.model.dao.LocationDao
import com.fjr619.weatherkmm.data.model.dao.asLocationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

class LocalDataSourceImpl(
    database: WeatherDatabase
): LocalDataSource {

    private val queries = database.weatherDatabaseQueries

    override fun getLocations(): Flow<List<LocationDao>> {
        return queries
            .getLocations(mapper = ::asLocationDao).asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun insertLocation(location: LocationDao) {
        queries.transaction {
            queries.insertLocationEntity(
                id = null,
                location = location.location,
                createdAt = Clock.System.now().toEpochMilliseconds(),
            )
        }
    }

    override suspend fun deleteLocation(id: Long) {
        queries.transaction {
            queries.deleteLocation(id)
        }
    }

    override suspend fun getLocationById(location: String): List<LocationDao> {
        return queries.getLocationByName(location, mapper = ::asLocationDao).executeAsList()
    }
}