package com.ttllab.quake_compose.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun QuakeContent(
    modifier: Modifier = Modifier,
    rotationEnabled: Boolean = true,
    positionEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val sensorController = remember { SensorController() }

    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .rotate(if (rotationEnabled) sensorController.rotationValue.value.z else 0f)
                .offset(
                    x = if (positionEnabled) sensorController.accelerationValue.value.x.dp else 0.dp,
                    y = if (positionEnabled) sensorController.accelerationValue.value.y.dp else 0.dp
                )
        ) {
            content()
        }
    }
}
