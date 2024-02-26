package com.fjr619.weatherkmm.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        dataModule,
        domainMidule
    )
}

//called by iOS etc
fun initKoin() = initKoin { }