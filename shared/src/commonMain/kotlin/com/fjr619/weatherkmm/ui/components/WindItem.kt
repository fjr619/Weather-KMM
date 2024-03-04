package com.fjr619.weatherkmm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.weatherkmm.MR
import com.fjr619.weatherkmm.ui.model.HourUi
import com.fjr619.weatherkmm.ui.screens.today.TodayWeatherUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WindItem(
    modifier: Modifier = Modifier,
    hourlyForecasts: List<HourUi>,
    headerSection: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitle(title = stringResource(MR.strings.wind))
        headerSection()
        HourlyWindItem(hourlyForecasts = hourlyForecasts)
    }
}

@Composable
fun WindToday(
    modifier: Modifier = Modifier,
    todayWeatherUiState: TodayWeatherUiState
) {

    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = todayWeatherUiState.windSpeed.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = todayWeatherUiState.windSpeedColor,
            fontSize = 56.sp,
        )

        Column(
                modifier = Modifier.alignBy(LastBaseline),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(-90f + todayWeatherUiState.windDirectionDegrees)
            )
            Text(
                text = stringResource(MR.strings.km_h),
                style = MaterialTheme.typography.labelMedium,
            )

        }

        Column(
            modifier = Modifier.padding(start = 16.dp).alignBy(LastBaseline),
        ) {
            Text(
                text = stringResource(todayWeatherUiState.windSpeedDescription),
            )
            todayWeatherUiState.windDirection?.let {
                Text(
                    text = stringResource(todayWeatherUiState.windDirection),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }

}