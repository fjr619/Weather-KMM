package com.fjr619.weatherkmm.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CurrentDateItem(
    currentTime: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = currentTime,
    )
}