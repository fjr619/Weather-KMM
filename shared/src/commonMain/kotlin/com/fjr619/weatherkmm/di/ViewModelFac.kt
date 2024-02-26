package com.fjr619.weatherkmm.di

import com.fjr619.weatherkmm.ui.screens.main.MainViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

//object ViewModelFac: KoinComponent {
//    fun getMainVMFactory(factory: PermissionsControllerFactory) =
//        viewModelFactory {
//            MainViewModel(factory.createPermissionsController())
//        }
//}