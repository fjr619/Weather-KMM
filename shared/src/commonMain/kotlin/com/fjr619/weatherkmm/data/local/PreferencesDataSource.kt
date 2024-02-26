package com.fjr619.weatherkmm.data.local

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val location: Flow<String>

    suspend fun saveLocation(location: String)
}