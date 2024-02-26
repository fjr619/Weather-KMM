package com.fjr619.weatherkmm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.fjr619.weatherkmm.WeatherDatabase
import com.fjr619.weatherkmm.data.local.datastore.DataStoreProvider
import com.fjr619.weatherkmm.data.local.DataStoreProviderImpl
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<DataStoreProvider> { DataStoreProviderImpl() }
    single<SqlDriver> {
        NativeSqliteDriver(WeatherDatabase.Schema, "WeatherDatabase.db")
    }
    single { Darwin.create() }
}