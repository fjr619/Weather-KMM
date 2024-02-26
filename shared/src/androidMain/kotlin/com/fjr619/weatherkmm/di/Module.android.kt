package com.fjr619.weatherkmm.di

import com.fjr619.weatherkmm.data.local.DataStoreProvider
import com.fjr619.weatherkmm.data.local.DataStoreProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<DataStoreProvider> { DataStoreProviderImpl(androidContext()) }
}