package com.fjr619.weatherkmm.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.fjr619.weatherkmm.data.local.datastore.DataStoreProvider
import com.fjr619.weatherkmm.data.local.datastore.createDataStore
import com.fjr619.weatherkmm.data.local.datastore.dataStoreFileName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DataStoreProviderImpl(
    context: Context,
): DataStoreProvider {
    override val dataStore: DataStore<Preferences> = createDataStore(
        coroutineScope = CoroutineScope(Job() + Dispatchers.IO),
        producePath = {
            context.filesDir.resolve(dataStoreFileName).absolutePath
        }
    )
}