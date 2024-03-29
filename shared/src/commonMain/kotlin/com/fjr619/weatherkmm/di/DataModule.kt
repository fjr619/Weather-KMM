package com.fjr619.weatherkmm.di

import com.fjr619.weatherkmm.WeatherDatabase
import com.fjr619.weatherkmm.data.local.datastore.PreferencesDataSource
import com.fjr619.weatherkmm.data.local.datastore.PreferencesDataSourceImpl
import com.fjr619.weatherkmm.data.local.db.LocalDataSource
import com.fjr619.weatherkmm.data.local.db.LocalDataSourceImpl
import com.fjr619.weatherkmm.data.remote.RemoteDataSource
import com.fjr619.weatherkmm.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    single<PreferencesDataSource> { PreferencesDataSourceImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(
        database = WeatherDatabase(driver = get())
    ) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}