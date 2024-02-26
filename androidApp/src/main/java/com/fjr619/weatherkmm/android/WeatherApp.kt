package com.fjr619.weatherkmm.android

import android.app.Application
import com.fjr619.weatherkmm.data.local.DataStoreProviderImpl
import com.fjr619.weatherkmm.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WeatherApp: Application(){

    override fun onCreate() {
        super.onCreate()
         initKoin {
             androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
             androidContext(this@WeatherApp)
         }
    }
}