package com.fjr619.weatherkmm.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface DataStoreProvider {

    val dataStore: DataStore<Preferences>
}