package com.fjr619.weatherkmm.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fjr619.weatherkmm.ui.model.HourUi
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.fjr619.weatherkmm.ui.screens.today.getWindColor

@Composable
fun HourlyWindItem    (
    modifier: Modifier = Modifier,
    hourlyForecasts: List<HourUi>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(hourlyForecasts) { hour ->
            HourlyWindForecast(
                hourlyForecast = hour,
                color = getWindColor(hour.windSpeed),
            )
        }
    }
}

@Composable
fun HourlyWindForecast(
    hourlyForecast: HourUi,
    color: Color,
    modifier: Modifier = Modifier,
) {

    //autostart
    val barHeightAnimation = remember {
        Animatable(0f)
    }
    LaunchedEffect(Unit) {
        barHeightAnimation.animateTo(
            hourlyForecast.windSpeed.coerceAtMost(32).toFloat(),
            tween(1000)
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .rotate(-90f + hourlyForecast.windDirectionDegrees)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = hourlyForecast.windSpeed.toString(),
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .height(32.dp)
                .width(16.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .height(barHeightAnimation.value.toInt().dp)
                    .width(16.dp)
                    .background(color = color)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

