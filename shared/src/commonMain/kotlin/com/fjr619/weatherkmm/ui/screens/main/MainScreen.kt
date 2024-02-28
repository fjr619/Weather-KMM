package com.fjr619.weatherkmm.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fjr619.weatherkmm.MR
import com.fjr619.weatherkmm.ui.screens.today.TodayWeatherScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val factory = rememberPermissionsControllerFactory()
    val viewModel: MainViewModel = getViewModel(
        key = "main-vm",
//        factory = ViewModelFac.getMainVMFactory(factory)
        factory = viewModelFactory {
            MainViewModel(factory.createPermissionsController())
        }
    )

    val state by viewModel.state.collectAsState()

    BindEffect(viewModel.permissionsController)

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {}
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                if (state.showEmptyMessage) {
                    EmptyLocationMessage()
                } else {
                    TodayWeatherScreen(mainUiState = state)
                }
            }
        }

    }
}

@Composable
fun EmptyLocationMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(MR.strings.empty_query_message))
    }
}