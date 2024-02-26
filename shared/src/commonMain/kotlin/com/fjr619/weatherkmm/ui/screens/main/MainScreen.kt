package com.fjr619.weatherkmm.ui.screens.main

import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory


@Composable
fun MainScreen() {
    val factory = rememberPermissionsControllerFactory()
    val viewModel: MainViewModel = getViewModel(
        key = "main-vm",
//        factory = ViewModelFac.getMainVMFactory(factory)
        factory = viewModelFactory {
            MainViewModel(factory.createPermissionsController())
        }
    )

    BindEffect(viewModel.permissionsController)
}