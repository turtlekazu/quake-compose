package com.ttllab.quake_compose.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    val accelerationValue by sensorController.accelerationValue
    val rotationValue by sensorController.rotationValue

    val rotationZ: Float by animateFloatAsState(rotationValue.z)
    val offsetX: Float by animateFloatAsState(-accelerationValue.x * 2f)
    val offsetY: Float by animateFloatAsState(-accelerationValue.y * 2f)

    Box(
        modifier = modifier
            .rotate(if (rotationEnabled) rotationZ else 0f)
            .offset(
                x = if (positionEnabled) offsetX.dp else 0.dp,
                y = if (positionEnabled) offsetY.dp else 0.dp
            )
    ) {
        content()
    }
}
