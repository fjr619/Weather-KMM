package com.fjr619.weatherkmm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.fjr619.weatherkmm.WeatherDatabase
import com.fjr619.weatherkmm.data.local.datastore.DataStoreProvider
import com.fjr619.weatherkmm.data.local.DataStoreProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<DataStoreProvider> { DataStoreProviderImpl(androidContext()) }
    single<SqlDriver> { AndroidSqliteDriver(
        context = androidContext(),
        name = "WeatherDatabase.db",
        schema = WeatherDatabase.Schema
    ) }
}