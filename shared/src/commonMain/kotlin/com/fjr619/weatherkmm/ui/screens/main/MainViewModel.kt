package com.fjr619.weatherkmm.ui.screens.main

import com.fjr619.weatherkmm.data.local.datastore.PreferencesDataSource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel(
    val permissionsController: PermissionsController,
): ViewModel(), KoinComponent {

    // ga boleh akses langsung ke data layer, harus pakai domain layer
    val dataStore: PreferencesDataSource = get()

    private val permissions = MutableStateFlow(PermissionsState())
    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    private val locationPermissionsFlow = dataStore.location
        .combine(permissions) { location, permissions ->
            when {
                location.isEmpty() && (permissions.isDenied == true || permissions.isPermanentlyDenied == true) -> {
                    MainEvent.ShowEmptyMessage

                }
                location.isEmpty() && permissions.isGranted == true -> {
                    MainEvent.LoadForecastWithLocation(location)
                }
                else -> {
                    MainEvent.UpdateQuery(query = location)
                }
            }
        }

    init {
        viewModelScope.launch {
            locationPermissionsFlow.collectLatest { onEvent(it) }
        }

        viewModelScope.launch {
            when(permissionsController.getPermissionState(Permission.LOCATION)) {
                PermissionState.NotDetermined -> onEvent(MainEvent.RequestLocationPermission)
                PermissionState.Granted -> permissions.granted()
                else -> {}
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.RequestLocationPermission -> requestPermission()
            else -> {}
        }
    }

    private fun requestPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)
                permissions.granted()
            } catch (e: DeniedAlwaysException) {
                permissions.permanentlyDenied()
            } catch (e: DeniedException) {
                permissions.denied()
            } finally {
                permissionsController.getPermissionState(Permission.LOCATION)
                    .also {
                        if (it == PermissionState.NotDetermined) requestPermission()
                    }
            }
        }
    }
}