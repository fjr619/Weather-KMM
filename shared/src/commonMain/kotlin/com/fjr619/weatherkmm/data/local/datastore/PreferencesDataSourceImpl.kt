package com.fjr619.weatherkmm.data.local.datastore

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataSourceImpl(
    private val dataStoreProvider: DataStoreProvider,
) : PreferencesDataSource {

    private val keyLocation = stringPreferencesKey("selected-location")

    override val location: Flow<String> =
        dataStoreProvider.dataStore.data.map { preferences -> preferences[keyLocation].orEmpty() }

    override suspend fun saveLocation(location: String) {
        dataStoreProvider.dataStore.edit { preferences ->
            preferences[keyLocation] = location
        }
    }
}